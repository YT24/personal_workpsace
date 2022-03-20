package com.example.yangt.commservice;

import com.example.yangt.middleWare.redis.RedisSonLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ControllerService {

    @Autowired
    private RedisSonLock redisService;

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public boolean checkTokenExpire(String token){

        return redisService.checkTokenExpire(token);
    }

    public void setRedisToken(Map<String,Object> data, HttpServletRequest request){
        /**
         * 将token存入session
         */
        request.getSession().setAttribute("Authentication",data.get("token"));
        /**
         * 将token存入redis
         */
        redisService.set((String) data.get("token"),data.get("token"), (Long) data.get("expire_in"),TimeUnit.SECONDS);
    }
}
