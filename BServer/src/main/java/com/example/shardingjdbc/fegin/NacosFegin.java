package com.example.shardingjdbc.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "AServer")
public interface NacosFegin {

    @RequestMapping(value = "/nacos/one")
    String login();

}
