package com.hokoory.hopass.config;

import com.hokoory.hopass.pass.service.impl.RedisServiceImpl;
import com.hokoory.hopass.pass.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckRedisConnectionJob {
    @Autowired
    RedisServiceImpl redisService;

    @Scheduled(cron = "*/60 * * * * ?")
    public void checkRedisConnectionJob() {
        try {
            redisService.checkConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
