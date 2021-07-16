package com.hokoory.hopass.pass.service;

public interface ITokenService {
    /**
     * 生成一个token
     * @param str
     * @return
     */
    String generatorToken(String str);

    /**
     * 直接设置key-value 过期时间为62208000秒
     * @param key
     * @param value
     */
    void setToken(String key, Object value);

    /**
     * 直接设置key-value 过期时间自己设置
     * @param key
     * @param value
     * @param timeout
     */
    void setToken(String key, Object value,long timeout);

    /**
     * 通过key获取token
     * @param key
     * @return
     */
    Object getToken(String key);
}
