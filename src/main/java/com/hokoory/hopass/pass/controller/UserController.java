package com.hokoory.hopass.pass.controller;


import com.hokoory.hopass.pass.entity.Config;
import com.hokoory.hopass.pass.entity.Response;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.entity.UserToken;
import com.hokoory.hopass.pass.mapper.ConfigMapper;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.pass.service.ITokenService;
import com.hokoory.hopass.pass.service.impl.UserServiceImpl;
import com.hokoory.hopass.utils.AESUtil;
import com.hokoory.hopass.utils.HexEncodeUtil;
import com.hokoory.hopass.utils.StringUtil;
import com.hokoory.hopass.utils.XORUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
public class UserController  {
    @Autowired
    ITokenService tokenService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<UserToken> login(@RequestParam(name = "username", required = false) String username,
                                     @RequestParam(name = "password", required = false) String password,
                                     @RequestHeader(name = "token", required = false, defaultValue = "0") String token) {
        UserToken tuser;
        tuser = (UserToken) tokenService.getToken(token);
        if (tuser != null) {
            return new Response<>(tuser);
        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", username)
//                .select("id", "password", "user_name", "salt", "ban_time", "create_time");
//        User user = userMapper.selectOne(queryWrapper);
        UserToken userToken = userService.login(username, password);
        return new Response<>(userToken);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response<User> signUp(@RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", username)
//                .select("id", "password", "user_name", "salt", "ban_time", "create_time");
//        User user = userMapper.selectOne(queryWrapper);
        User user = userService.signup(username,password);
        return new Response<>(user);
    }

    @RequestMapping(value = "/cansignup", method = RequestMethod.GET)
    public Response<Map<String,String>> canSignUp() {
//        QueryWrapper<Config> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("config_key", "cansignup");
//        Config config = configMapper.selectOne(queryWrapper);
        return new Response<>(userService.canSignUp());
    }
}

