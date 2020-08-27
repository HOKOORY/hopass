package com.hokoory.hopass.pass.enums;

public enum ErrorCodeAndMsg {
    User_input_fail("-1", "用户名输入错误"),
    User_ban("-1", "该用户被Ban了"),
    User_password_error("-1", "密码输入错误"),
    Unknow_error("-1", "未知错误"),
    User_name_in_database("-1", "该用户名已存在"),
    User_signup_error("-1", "注册失败，请联系管理员"),
    Network_error("9999", "网络错误，待会重试"),
    ;

    private String code;
    private String msg;

    ErrorCodeAndMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }
}
