package com.example.yangt.middleWare.redis.subscribe;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisConsumer implements MessageListener {
	

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("redis consumer channel:"+message.getChannel().toString());
        System.out.println("redis consumer message："+message.toString());
    }
}