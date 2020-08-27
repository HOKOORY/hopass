package com.hokoory.hopass.pass.controller;


import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.mapper.PasswordMapper;
import com.hokoory.hopass.pass.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hokoory
 * @since 2020-08-21
 */
@RestController
@RequestMapping("/pass")
public class PasswordController {
    @Autowired
    PasswordMapper passwordMapper;
    @Autowired
    TokenServiceImpl tokenService;

    public Object setPassword(@RequestParam(name = "title") String title,
                              @RequestParam(name = "context") String context,
                              @RequestParam(name = "web") String web,
                              @RequestParam(name = "account") String account,
                              @RequestParam(name = "password") String password,
                              @RequestHeader(name = "token") String token) {
        User user = (User) tokenService.getToken(token);

        return null;
    }
}

