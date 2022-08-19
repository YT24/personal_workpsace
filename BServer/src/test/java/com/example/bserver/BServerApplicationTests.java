package com.example.bserver;

import com.example.bserver.entity.Goods;
import com.example.bserver.jwt.config.JwtService;
import com.example.bserver.mapper.GoodsMapper;
import com.example.bserver.service.GoodsService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@MapperScan("com.example.bserver.mapper")
class BServerApplicationTests {

    @Autowired
    JwtService jwtService;

    @Autowired
    private GoodsService service;

    @Test
    void contextLoads() throws InterruptedException {
        String token = jwtService.createToken("1234-21345-2134-2134");
        System.out.println(token);
//        Thread.sleep(10000);
        String jwt ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjM0LTIxMzQ1LTIxMzQtMjEzNCIsImlhdCI6MTY1Mzk3ODA4NSwiZXhwIjoxNjUzOTc4MTM1fQ.3td2CUpCoWr7H9zsP9TYdcxVbapscIXr-IRB2QEoO8ZXMTfe-3db0UZ27Aj8KM_LbpHXKG9032JqNV5hJgz39Q";
        Claims tokenClaim = jwtService.getTokenClaim(jwt);
    }

    @Test
    void contextLoads2() throws InterruptedException {
        service.goods();
    }


}
