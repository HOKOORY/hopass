package com.hokoory.hopass.pass.interceptor;

import com.hokoory.hopass.exception.UserException;
import com.hokoory.hopass.pass.entity.User;
import com.hokoory.hopass.pass.enums.ErrorCodeAndMsg;
import com.hokoory.hopass.pass.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    TokenServiceImpl tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 验证是否登录
        System.out.println("preHandle run!");
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        String token = httpServletRequest.getHeader("token");
        if (null == token) {
            throw new UserException(ErrorCodeAndMsg.User_not_login);
        }
        User user = (User) tokenService.getToken(token);
        if (null == user) {
            throw new UserException(ErrorCodeAndMsg.User_not_login);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle run!");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion run!");
    }
}