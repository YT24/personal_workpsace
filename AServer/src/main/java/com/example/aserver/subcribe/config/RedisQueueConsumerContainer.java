package com.example.aserver.subcribe.config;

import com.example.aserver.subcribe.pc.RedisQueueConsumer;
import com.example.aserver.subcribe.RedisQueueListener;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RedisQueueConsumerContainer {

    //存放消费者的map  key=消费者 queueName value=消费者对象
    Map<String, RedisQueueConsumer> consumerMap = new HashMap<>();

    // 容器是否初始化完毕的标示
    public static Boolean isRun = false;
    public  MyRedisThreadPool myRedisThreadPool;

    public void addConsumer(RedisQueueConsumer consumer) {
        if (consumer.getQueueName() == null) {
            log.error("【添加redis队列失败】:{}", "队列名称为null");
        } else if (null == consumerMap.get(consumer.getQueueName())) {
            consumerMap.put(consumer.getQueueName(), consumer);
            log.error("【添加redis队列成功】:{}", consumer.getQueueName());
        }
    }

    public void destroy() {
        log.info("redis消费者容器销毁");
        myRedisThreadPool.destroy();
    }

    public void init() {
        log.info("redis消费者容器初始化开始");
        isRun = true;
        myRedisThreadPool = MyRedisThreadPool.getInstance(consumerMap.size());
        consumerMap.forEach((k, v) -> {
            myRedisThreadPool.executor(new RedisQueueListener(v));
        });
    }
}