package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.exception.UserException;
import com.hokoory.hopass.pass.entity.UserToken;
import com.hokoory.hopass.pass.enums.ErrorCodeAndMsg;
import com.hokoory.hopass.pass.mapper.UserMapper;
import com.hokoory.hopass.utils.HexEncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Future;

@Component
public class AsyncTask {
    @Autowired
    UserMapper userMapper;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("asyncServiceExecutor")
    public Future<UserToken> getUserInfo(Map map) throws InterruptedException {
        UserToken user = userMapper.getUserByUsername(map);
        return new AsyncResult<>(user);
    }

    @Async("asyncServiceExecutor")
    public Future<String> MD5encrypt(String str) throws InterruptedException {
        String MD5pass;
        try {
            // 计算密码是否正确
            MD5pass = HexEncodeUtil.Md5Encode(str);
        } catch (Exception e) {
            throw new UserException(ErrorCodeAndMsg.Unknow_error);
        }
        return new AsyncResult<>(MD5pass);
    }
}
