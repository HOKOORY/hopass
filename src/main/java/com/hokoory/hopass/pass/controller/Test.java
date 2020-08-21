package com.hokoory.hopass.pass.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hokoory.hopass.pass.entity.Config;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.utils.XORUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;


@RestController
public class Test {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @RequestMapping("/test")
    public Object test() {
        String key = "user:1";
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", "admin"));
        redisTemplate.opsForValue().set(key, user);
        User user1 = (User) redisTemplate.opsForValue().get(key);
        return XORUtils.decrypt(user1.getKeygen().getBytes(), (user1.getId() + user1.getUserName()).getBytes());
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name","admin"));
    }
}
