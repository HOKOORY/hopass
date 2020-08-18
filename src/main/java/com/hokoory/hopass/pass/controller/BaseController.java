package com.hokoory.hopass.pass.controller;


import net.sf.json.JSONObject;

public abstract class BaseController {
    protected JSONObject error(int code, String msg, Object data) {
        JSONObject json = new JSONObject();
        json.put("ResultCode", code);
        json.put("ErrorMsg", msg);
        json.put("data", data);
        return json;
    }

    protected JSONObject error(int code, String msg) {
        JSONObject json = new JSONObject();
        return error(code, msg,null);
    }

    protected JSONObject success(Object data, String msg, int code) {
        JSONObject json = new JSONObject();
        json.put("ResultCode", code);
        json.put("ErrorMsg", msg);
        json.put("Data", data);
        return json;
    }

    protected JSONObject success(Object data, String msg) {
        int code = 200;
        return success(data, msg, code);
    }

    protected JSONObject success(Object data) {
        String msg = "";
        return success(data, msg);
    }
}
