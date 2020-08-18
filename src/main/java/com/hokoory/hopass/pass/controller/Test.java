package com.hokoory.hopass.pass.controller;

import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.utils.XORUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class Test {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/test")
    public Object test() {
        User user = userMapper.selectOne(null);
        return XORUtils.decrypt(user.getKeygen().getBytes(),(user.getId()+user.getUserName()).getBytes());
    }
}
