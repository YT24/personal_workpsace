package com.example.client.feign;

import com.example.client.feign.config.FeignConfig;
import com.example.client.swagger2.entity.LoginRequestData;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

@FeignClient(
        value = "server-service",
        configuration = FeignConfig.class
)
public interface LoginService {

    @RequestLine("POST /server/login")
    String login(LoginRequestData loginRequestData);
}
