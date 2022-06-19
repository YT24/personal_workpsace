package com.example.shardingjdbc.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.shardingjdbc.annotation.Decrypt;
import com.example.shardingjdbc.annotation.Encrypt;
import com.example.shardingjdbc.config.properties.EncryptProperties;
import com.example.shardingjdbc.expection.ResponseResult;
import com.example.shardingjdbc.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yangte
 */
@RestController
public class EncryptDecryptController {

    @Autowired
    private EncryptProperties encryptProperties;


    @PostMapping("/user")
    @Encrypt // 响应参数加密    @Decrypt 请求参数解密
    public ResponseResult addUser(@RequestBody  @Decrypt Map user) throws Exception {
        System.out.println(AESUtils.encrypt(JSONObject.toJSONString(user).getBytes(), encryptProperties.getKey().getBytes()));
        System.out.println(user);
        return ResponseResult.success(user);
    }
}