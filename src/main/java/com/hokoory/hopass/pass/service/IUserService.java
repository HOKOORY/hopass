package com.hokoory.hopass.pass.service;

import com.hokoory.hopass.pass.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hokoory.hopass.pass.entity.UserToken;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hokoory
 * @since 2020-08-05
 */
public interface IUserService extends IService<User> {
    UserToken login(String username,String password);
    User signup(String username,String password);
    Map<String,String> canSignUp();

}
