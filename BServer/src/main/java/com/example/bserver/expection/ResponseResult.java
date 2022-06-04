package com.example.bserver.expection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2021/11/8 13:23.
 *
 * @author xiao
 * Description:
 */
@ToString
@NoArgsConstructor
@ApiModel(value = "返回内容", description = "接口返回的对象内容")
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -1528921062052319020L;

    private ResponseResult(ReturnCode returnCode) {
        this.code = returnCode.getReturnCode();
        this.msg = returnCode.getMessage();
    }


    @ApiModelProperty(value = "返回状态（200：成功 500：失败）", example = "0", position = 1)
    private int code;


    @ApiModelProperty(value = "返回信息", example = "成功", position = 2)
    private String msg;

    @ApiModelProperty(value = "返回数据", position = 3)
    private T data;

    private ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public void setResponseData(T data){
        this.data = data;
    }

    /**
     * 成功返回
     *
     * @return Result
     */
    public static ResponseResult success() {
        return new ResponseResult(ReturnCode.OK);
    }

    /**
     * 成功返回并携带数据
     *
     * @return Result
     */
    public static ResponseResult success(Object data) {
        return  success().setData(data);
    }

    /**
     * 错误返回
     *
     * @param message 错误消息
     * @return Result
     */
    public static ResponseResult fail(String message) {
        ResponseResult result = new ResponseResult(ReturnCode.ERROR);
        result.msg = message;
        return result;
    }

    /**
     * 错误返回
     *
     * @param returnCode
     * @return Result
     */
    public static ResponseResult fail(ReturnCode returnCode) {
        return new ResponseResult(returnCode);
    }

    /**
     * 错误返回
     *
     * @param returnCode
     * @param message
     * @return Result
     */
    public static ResponseResult fail(ReturnCode returnCode, String message) {
        ResponseResult result = new ResponseResult(returnCode);
        result.msg = message;
        return result;
    }

    /**
     * 静态枚举限制写入任意值
     */
    public static class ReturnCode {
        public static final ReturnCode OK = new ReturnCode(200, "成功");
        public static final ReturnCode ERROR = new ReturnCode(500, "失败");
        @Getter
        private Integer returnCode;
        @Getter
        private String message;

        public ReturnCode() {
        }

        private ReturnCode(Integer returnCode) {
            this.returnCode = returnCode;
        }

        private ReturnCode(Integer returnCode, String message) {
            this.returnCode = returnCode;
            this.message = message;
        }
    }
}
