package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.pass.service.ITokenService;
import com.hokoory.hopass.utils.HexEncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;


@Service
public class TokenServiceImpl implements ITokenService {
    @Autowired
    RedisTemplate<String, Object> objectRedisTemplate;

    @Override
    public String generatorToken(String str) {
        String token = "";
        try {
            token = HexEncodeUtil.Md5Encode(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void setToken(String key, Object value) {
        objectRedisTemplate.opsForValue().set(key, value,62208000, TimeUnit.SECONDS);
    }

    @Override
    public void setToken(String key, Object value, long timeout) {
        objectRedisTemplate.opsForValue().set(key, value,timeout, TimeUnit.SECONDS);
    }

    @Override
    public Object getToken(String key) {
        return objectRedisTemplate.opsForValue().get(key);
    }

}
