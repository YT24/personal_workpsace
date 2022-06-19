package com.example.shardingjdbc;

import com.example.shardingjdbc.jwt.config.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BServerApplicationTests {

    @Autowired
    JwtService jwtService;

    @Test
    void contextLoads() throws InterruptedException {
        String token = jwtService.createToken("1234-21345-2134-2134");
        System.out.println(token);
//        Thread.sleep(10000);
        String jwt ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjM0LTIxMzQ1LTIxMzQtMjEzNCIsImlhdCI6MTY1Mzk3ODA4NSwiZXhwIjoxNjUzOTc4MTM1fQ.3td2CUpCoWr7H9zsP9TYdcxVbapscIXr-IRB2QEoO8ZXMTfe-3db0UZ27Aj8KM_LbpHXKG9032JqNV5hJgz39Q";
        Claims tokenClaim = jwtService.getTokenClaim(jwt);
    }

}
