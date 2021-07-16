package com.hokoory.hopass.config;

import com.hokoory.hopass.pass.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    //在此处，将拦截器注册为一个 Bean
    @Bean
    public LoginInterceptor LoginInterceptor() {
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(LoginInterceptor());
        registration.addPathPatterns("/**");
        ArrayList<String> list = new ArrayList<>();
        list.add("/user/login");
        list.add("/user/cansignup");
        list.add("/user/signup");
        list.add("/test/**");
        list.add("/test");
        registration.excludePathPatterns(list);
    }
}
