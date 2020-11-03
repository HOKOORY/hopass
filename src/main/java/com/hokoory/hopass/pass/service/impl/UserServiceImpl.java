package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.exception.UserException;
import com.hokoory.hopass.pass.entity.Config;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.entity.UserToken;
import com.hokoory.hopass.pass.enums.ErrorCodeAndMsg;
import com.hokoory.hopass.pass.mapper.ConfigMapper;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.pass.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hokoory.hopass.utils.AESUtil;
import com.hokoory.hopass.utils.HexEncodeUtil;
import com.hokoory.hopass.utils.StringUtil;
import com.hokoory.hopass.utils.XORUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hokoory
 * @since 2020-08-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ConfigMapper configMapper;
    @Autowired
    TokenServiceImpl tokenService;

    @Override
    public UserToken login(String username,String password) {
        Map<String, String> map = new HashMap<>();
        map.put("user_name", username);
        UserToken user = userMapper.getUserByUsername(map);
        if (user == null) {
            throw new UserException(ErrorCodeAndMsg.User_input_fail);
        }
        if (user.getBanTime() != 0) {
            throw new UserException(ErrorCodeAndMsg.User_ban);
        }
        String MD5pass = null;
        String user_pass = user.getPassword();
        String user_salt = user.getSalt();
        try {
            // 计算密码是否正确
            MD5pass = HexEncodeUtil.Md5Encode(password + user_salt);
        } catch (Exception e) {
            throw new UserException(ErrorCodeAndMsg.Unknow_error);
        }
        if (!MD5pass.equals(user_pass)) {
            throw new UserException(ErrorCodeAndMsg.User_password_error);
        }
        // 生成token
        String token = tokenService.generatorToken(user.getId() + user.getUserName() + StringUtil.randomString(12));
        // 设置token
        user.setToken(token);
        tokenService.setToken(token, user);
        user.setKeygen("");
        user.setPassword("");
        user.setSalt("");
        return user;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public User signup(String username,String password) {
        Map<String, String> map = new HashMap<>();
        map.put("user_name", username);
        User user = userMapper.getUserByUsername(map);
        // 判断用户名是否存在数据库，存在则抛出异常
        if (user != null) {
            throw new UserException(ErrorCodeAndMsg.User_name_in_database);
        }
        // 生成4位数随机盐
        String salt = StringUtil.randomString(4);
        String MD5_pass = "";
        try {
            MD5_pass = HexEncodeUtil.Md5Encode(password + salt);
        } catch (Exception e) {
            throw new UserException(ErrorCodeAndMsg.Unknow_error);
        }
        User iuser = new User();
        iuser.setUserName(username);
        iuser.setPassword(MD5_pass);
        iuser.setSalt(salt);
        iuser.setCreateTime((int) (new Date().getTime() / 1000));
        userMapper.insertUserBySignUp(iuser);
//        userMapper.insert(iuser);
//        user = userMapper.selectOne(queryWrapper);
        // 生成genkey 用于aes加密用户的密码
        String genkey = AESUtil.KeyGenerator();
        if (null == genkey) {
            throw new UserException(ErrorCodeAndMsg.User_signup_error);
        }
        // 异或加密， 密钥是 id + username
        iuser.setKeygen(new String(XORUtils.encrypt(genkey.getBytes(), (iuser.getId() + iuser.getUserName()).getBytes())));
        // 密钥二次加密保存在数据库
        userMapper.updateUserBySignUp(iuser);
//        userMapper.update(iuser, queryWrapper);
        // 将关键信息取消绑定
        iuser.setKeygen("");
        iuser.setSalt("");
        iuser.setPassword("");
        return iuser;
    }

    @Override
    public Map<String,String> canSignUp() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "cansignup");
        Config config = configMapper.getOneConfigByKey(map);
        Map<String,String> resMap = new HashMap<>();
        if ("0".equals(config.getConfigValue())) {
            resMap.put("is_can","0");
            resMap.put("msg","不允许注册账号");
        } else if ("1".equals(config.getConfigValue())) {
            resMap.put("is_can","1");
            resMap.put("msg","允许注册账号");
        } else {
            throw new UserException(ErrorCodeAndMsg.Network_error);
        }
        return resMap;
    }
}
