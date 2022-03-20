package com.example.bserver.controller;

import com.example.bserver.fegin.NacosFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // nacos 配置修改时自动刷新
@RestController
public class ConsumerController {


    @Autowired
    private NacosFegin nacosFegin;


    @Value("${login.username:null}")
    private String username;


    @GetMapping("/test")
    public String login(){
        System.out.println("username："+username);
        return nacosFegin.login();
    }
}
