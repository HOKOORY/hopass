package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.entity.UserToken;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.pass.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public UserToken getUserByUsername(Map<String, String> map) {
        return userMapper.getUserByUsername(map);
    }

    @Override
    public int updateUserBySignUp(User user) {
        return userMapper.updateUserBySignUp(user);
    }

    @Override
    public int insertUserBySignUp(User user) {
        return userMapper.insertUserBySignUp(user);
    }

    @Override
    public User getUserInPass(Map<String, String> map) {
        return userMapper.getUserInPass(map);
    }
}
