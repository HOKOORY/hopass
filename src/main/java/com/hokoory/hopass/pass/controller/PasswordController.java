package com.hokoory.hopass.pass.controller;


import com.hokoory.hopass.pass.entity.Password;
import com.hokoory.hopass.pass.entity.Response;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.mapper.PasswordMapper;
import com.hokoory.hopass.pass.service.impl.TokenServiceImpl;
import com.hokoory.hopass.utils.AESUtil;
import com.hokoory.hopass.utils.XORUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
public class PasswordController{
    @Autowired
    PasswordMapper passwordMapper;
    @Autowired
    TokenServiceImpl tokenService;

    @RequestMapping("/set")
    public Response setPassword(@RequestParam(name = "title") String title,
                                @RequestParam(name = "context") String context,
                                @RequestParam(name = "web") String web,
                                @RequestParam(name = "account") String account,
                                @RequestParam(name = "password") String password,
                                @RequestHeader(name = "token") String token) {
        User user = (User) tokenService.getToken(token);
        int time = (int) (new Date().getTime() / 1000);
        String keygen = new String(XORUtils.decrypt(user.getKeygen().getBytes(), (user.getId() + user.getUserName()).getBytes()));
        password = AESUtil.AEGCMEncrypt(password, keygen);
        Password passwordEntity = new Password();
        passwordEntity.setPassword(password);
        passwordEntity.setAccount(account);
        passwordEntity.setContext(context);
        passwordEntity.setCreateTime(time);
        passwordEntity.setTitle(title);
        passwordEntity.setWeb(web);
        passwordEntity.setUserId(user.getId());
        passwordEntity.setUpdateTime(time);
        int id = passwordMapper.insertPassword(passwordEntity);

        return new Response(id);
    }

    @RequestMapping("/getlist")
    public Response getPasswordList(@RequestHeader(name = "token") String token) {
        User user = (User) tokenService.getToken(token);

        return null;
    }
    @RequestMapping("/getdetail")
    public Response getPasswordDetail(@RequestParam(name = "id") String id,
            @RequestHeader(name = "token") String token) {
        User user = (User) tokenService.getToken(token);

        return null;
    }
}

