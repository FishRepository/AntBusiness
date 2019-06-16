package com.backend.admin.entity;

import java.io.Serializable;


public class AjaxResult<T> implements Serializable {

    private static final long serialVersionUID = 80629816069662096L;
    private String msg;

    private T data;

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
