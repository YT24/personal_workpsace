package com.example.yangt.pojo;

import java.io.Serializable;

public class ResultMessage implements Serializable {
    private static final long serialVersionUID = 6792616915033142245L;
    private String error_code = "0";
    private String error_msg;
    private Object data;

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return this.error_msg;
    }

    public void setError_Msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMessage [error_code=" + error_code + ", error_msg=" + error_msg + ", data=" + data + "]";
    }

}