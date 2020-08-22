package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.pass.service.ITokenService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements ITokenService {
    @Autowired
    RedisTemplate<String, Object> objectRedisTemplate;

    @Override
    public String generatorToken(String str) {

        return null;
    }

    @Override
    public String setToken(String key, Object value) {

        return null;
    }

    @Override
    public String getToken(String key) {

        return null;
    }

}
