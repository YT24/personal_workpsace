package com.example.yangt.middleWare.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 */
@Slf4j
@Component
public class RedisLock {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript defaultRedisScript;



    /**
     * 加锁
     * @param key   商品id
     * @param value 当前时间+设置多久超时的时间
     * @return
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {     //这个其实就是setnx命令，只不过在java这边稍有变化，返回的是boolea
            return true;
        }
        //避免死锁，且只让一个线程拿到锁
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        //如果锁过期了
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValues = (String) redisTemplate.opsForValue().getAndSet(key, value);

            /*
               只会让一个线程拿到锁
               如果旧的value和currentValue相等，只会有一个线程达成条件，因为第二个线程拿到的oldValue已经和currentValue不一样了
             */
            if (!StringUtils.isEmpty(oldValues) && oldValues.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("『redis分布式锁』解锁异常，{}", e);
        }
    }

    /**
     * 事务 原子性
     * @param key
     * @param value
     */
    public void unlockTransaction(String key, String value){
        while (true) {
            redisTemplate.watch(key);
            if (value.equals(redisTemplate.opsForValue().get(key))) {
                redisTemplate.setEnableTransactionSupport(true);//是否支持事务
                redisTemplate.multi();//开启事务
                redisTemplate.opsForValue().getOperations().delete(key);
                List<Object> objects = redisTemplate.exec();//提交事务
                if (objects == null) {
                    continue;
                }
            }
            redisTemplate.unwatch();//关闭
            break;
        }
    }

    /**
     *lua 解锁
     * @param key
     * @param value
     */
    public void unlockLua(String key, String value){

        Object kk = redisTemplate.opsForValue().get(key);
        Map<String,Object> argvMap = new HashMap<>();
        argvMap.put(value,Long.parseLong(value));
        Object object = redisTemplate.execute(defaultRedisScript,Collections.singletonList(key),argvMap);
        if("1".equals(object.toString())){
            System.out.println("------del redis lock ok");
        }else {
            System.out.println("------del redis lock error");
        }
    }
}