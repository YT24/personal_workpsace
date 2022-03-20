package com.example.yangt.middleWare.redis.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisProducer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //向通道发送消息的方法
    public void sendChannelMess(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
