package com.example.client.swagger2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ResultData<T> implements Serializable {

    @ApiModelProperty(value = "返回状态码；0:成功")
    private String error_code;

    @ApiModelProperty(value = "描述信息")
    private String error_msg;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public static <T> ResultData<T> error(String error_code, String error_msg) {
        return new ResultData(error_code, error_msg);
    }
    private ResultData(String error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }
    private ResultData(String error_code, String error_msg, T data) {
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

