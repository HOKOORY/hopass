package com.hokoory.hopass.pass.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserToken extends User{
    private String token;
}
