package com.example.yangt.enums;


public class CommonResult<T> {

    private String error_code;

    private String error_msg;

    private T data;


    public CommonResult(String error_code, String error_msg, T data) {
        this.error_code = error_code;
        this.error_msg = error_msg;
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
