package com.example.bserver.expection;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GloableExpectionHandler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object handlerException(Exception ex) {
        log.error("系统内部错误,服务器繁忙，请稍后重试: ", ex);
        return ResponseResult.fail("服务器繁忙，请稍后重试 : "+ex.getMessage()+ex.getClass().getName());
    }

    @ResponseBody
    @ExceptionHandler(value = ExpiredJwtException.class)
    public Object handlerExpiredJwtException(ExpiredJwtException ex) {
        log.error("JWT 过期: ", ex);
        return ResponseResult.fail(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = SignatureException.class)
    public Object handlerSignatureException(SignatureException ex) {
        log.error("JWT Header Expection: ", ex.getMessage());
        return ResponseResult.fail(ex.getMessage());
    }


    /**
     * 参数不匹配异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseResult<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("系统内部错误，参数不匹配: ", ex);
        return ResponseResult.fail("参数类型不匹配："+ex.getMessage());
    }

    /**
     * 参数绑定异常，类型不一致
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : errors) {
            String message = error.getDefaultMessage();
            stringBuilder.append(message).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        log.error("系统内部错误，方法参数校验错误:{} ", stringBuilder.toString());
        return ResponseResult.fail(stringBuilder.toString());
    }

}
