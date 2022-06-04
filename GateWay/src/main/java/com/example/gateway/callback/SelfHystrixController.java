package com.example.gateway.callback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SelfHystrixController {

    @RequestMapping("/defaultfallback")
    public Map<String,String> defaultfallback(){
        System.out.println("请求被熔断.");
        Map<String,String> map = new HashMap<>();
        map.put("code","500");
        map.put("message","服务响应超时");
        map.put("data","");
        return map;
    }

}