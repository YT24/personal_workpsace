package com.example.aserver.entity;


import java.io.Serializable;

/**
 * @Description: 响应信息主体
 * @author: LiFei
 * @Date: 2021/6/30 10:37 上午
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -3197059789017703937L;

    /**
     * 200 成功 非200错误
     */
    private int code;

    private String msg;

    private T data;



    public ResponseResult(int i, String s, T data) {
        this.code = i;
        this.msg = s;
        this.data = data;
    }

    public ResponseResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    /***
     * 构建ResponseResult 对象 - （有返回内容）
     *
     * @param code 状态码
     * @param msg  返回信息
     * @param data 返回内容
     * @return
     */
    private static <T> ResponseResult<T> response(int code, String msg,T data) {
        ResponseResult<T> apiResult = new ResponseResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }




}
