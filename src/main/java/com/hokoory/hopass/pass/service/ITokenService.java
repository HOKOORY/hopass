package com.hokoory.hopass.pass.service;

public interface ITokenService {
    String generatorToken(String str);

    void setToken(String key, Object value);

    Object getToken(String key);
}
