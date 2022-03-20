package com.example.yangt.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultMessage implements Serializable {
    private static final long serialVersionUID = 6792616915033142245L;
    private String error_code = "0";
    private String error_msg;
    private Object data;

    @Override
    public String toString() {
        return "ResultMessage [error_code=" + error_code + ", error_msg=" + error_msg + ", data=" + data + "]";
    }

}