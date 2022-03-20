package com.example.aserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProducerController {

    @GetMapping("/nacos/one")
    public String login(HttpServletRequest request){
        System.out.println(request.getHeader("Authorization"));
        System.out.println(request.getHeader("yangte"));
        return "调用nacos one 接口 成功 ！！！";
    }
}
