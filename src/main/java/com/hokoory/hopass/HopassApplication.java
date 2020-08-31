package com.hokoory.hopass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ImportResource(locations = {"classpath:web.xml"})
public class HopassApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopassApplication.class, args);
    }

}
