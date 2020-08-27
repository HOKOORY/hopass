package com.hokoory.hopass.pass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hokoory.hopass.pass.entity.Config;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.entity.UserToken;
import com.hokoory.hopass.pass.mapper.ConfigMapper;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.pass.service.ITokenService;
import com.hokoory.hopass.utils.AESUtil;
import com.hokoory.hopass.utils.HexEncodeUtil;
import com.hokoory.hopass.utils.StringUtil;
import com.hokoory.hopass.utils.XORUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hokoory
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ConfigMapper configMapper;
    @Autowired
    ITokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestParam(name = "username", required = false) String username,
                            @RequestParam(name = "password", required = false) String password,
                            @RequestHeader(name = "token", required = false) String token) {
        UserToken tuser;
        try {
            tuser = (UserToken) tokenService.getToken(token);
        }catch (Exception e){
            tuser = null;
        }
        if (tuser != null){
            return success(tuser);
        }
        System.out.println("token 失效");
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", username)
//                .select("id", "password", "user_name", "salt", "ban_time", "create_time");
//        User user = userMapper.selectOne(queryWrapper);
        Map<String,String> map = new HashMap<>();
        map.put("user_name",username);
        UserToken user = userMapper.getUserByUsername(map);
        if (user == null) {
            return error(-1, "用户名输入错误", "");
        }
        if (user.getBanTime() != 0) {
            return error(-1, "该用户被Ban了", "");
        }
        String sha1pass = null;
        String user_pass = user.getPassword();
        String user_salt = user.getSalt();
        try {
            sha1pass = HexEncodeUtil.Md5Encode(password + user_salt);
        } catch (Exception e) {
            return error(-1, e.getMessage(), "");
        }
        if (!sha1pass.equals(user_pass)) {
            return error(-1, "密码输入错误", "");
        }
        user.setPassword("");
        user.setSalt("");
        user.setKeygen("");
        token = tokenService.generatorToken(user.getId() + user.getUserName()+StringUtil.randomString(12));
        user.setToken(token);
        tokenService.setToken(token,user);
        return success(user);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public JSONObject signUp(@RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", username)
//                .select("id", "password", "user_name", "salt", "ban_time", "create_time");
//        User user = userMapper.selectOne(queryWrapper);
        Map<String, String> map = new HashMap<>();
        map.put("user_name", username);
        User user = userMapper.getUserByUsername(map);

        if (user != null) {
            return error(-1, "该用户名已存在", "");
        }
        String salt = StringUtil.randomString(4);
        String sha1_pass = "";
        try {
            sha1_pass = HexEncodeUtil.Md5Encode(password + salt);
        } catch (Exception e) {
            return error(-1, e.getMessage(), "");
        }
        User iuser = new User();
        iuser.setUserName(username);
        iuser.setPassword(sha1_pass);
        iuser.setSalt(salt);
        iuser.setCreateTime((int) (new Date().getTime() / 1000));
        userMapper.insertUserBySignUp(iuser);
//        userMapper.insert(iuser);
//        user = userMapper.selectOne(queryWrapper);
        String genkey = AESUtil.KeyGenerator();
        if (null == genkey) {
            return error(-1, "注册失败，请联系管理员", "");
        }
        // 异或加密， 密钥是 id + username
        iuser.setKeygen(new String(XORUtils.encrypt(genkey.getBytes(), (iuser.getId() + iuser.getUserName()).getBytes())));
        userMapper.updateUserBySignUp(iuser);
//        userMapper.update(iuser, queryWrapper);
        iuser.setKeygen("");
        iuser.setSalt("");
        iuser.setPassword("");
        return success(iuser);
    }

    @RequestMapping(value = "/cansignup", method = RequestMethod.GET)
    public Object canSignUp() {
//        QueryWrapper<Config> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("config_key", "cansignup");
//        Config config = configMapper.selectOne(queryWrapper);

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "cansignup");
        Config config = configMapper.getOneConfigByKey(map);
        if ("0".equals(config.getConfigValue())) {
            return success(0, "不允许注册账号");
        } else if ("1".equals(config.getConfigValue())) {
            return success(1, "允许注册账号");
        } else {
            return error(-1, "服务器错误");
        }
    }
}

