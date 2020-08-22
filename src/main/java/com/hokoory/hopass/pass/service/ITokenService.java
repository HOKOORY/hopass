package com.hokoory.hopass.pass.service;

public interface ITokenService {
    String generatorToken(String str);

    String setToken(String key, Object value);

    String getToken(String key);
}
