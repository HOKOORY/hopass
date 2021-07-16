package com.hokoory.hopass.pass.service.impl;

import com.hokoory.hopass.pass.service.IAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements IAsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executorAsync() {
        logger.info("start executoAsync");
        logger.info("end executeAsync");
    }
}
