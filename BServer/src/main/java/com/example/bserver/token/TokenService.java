package com.example.bserver.token;

import com.example.bserver.expection.AutoIdempotentExpection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class TokenService {


    private static Map cache = new HashMap();


    public String getToken(){
        String token = UUID.randomUUID().toString();
        cache.put(token,token);

        return token;
    }

    public boolean checkToken(String token) {
        if(StringUtils.isBlank(token)){
            throw new AutoIdempotentExpection("token 不存在");
        }
        if(Objects.isNull(cache.get(token))){
            throw new AutoIdempotentExpection("token 重复的操作");
        }

        cache.remove(token);
        return true;
    }





}
