package com.hokoory.hopass.pass.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumerListener {
    @JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
    public void readActiveQueue(String message) {
        System.out.println("queue接受到：" + message);
    }
}
