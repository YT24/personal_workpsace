package com.example.yangt.middleWare.redis;


import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisSonLock {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 获取锁
     * @param key
     * @return
     */
    public RLock rLock(String key){

        return this.redissonClient.getLock(key);
    }

    /**
     * 分布式加锁
     * @param key 加锁的key
     */
    public void lock(String key) {
        RLock lock = this.rLock(key);
        if(!lock.isLocked()){
            //lock.lock();
            lock.lock(30,TimeUnit.SECONDS);
        }
    }


    /**
     * 分布式解锁
     * @param key 加锁的key
     */
    public void unLock(String key){
        RLock lock = this.rLock(key);
        if(lock.isLocked()){
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    };

    /**
     * 判断是否加锁
     * @param key
     * @return
     */
    public boolean isLock(String key){

        return this.rLock(key).isLocked();
    }

    /**
     * 判断token是否有效
     * @param token 登录凭证
     * @return
     */
    public boolean checkTokenExpire(String token){

        long times = redisTemplate.opsForValue().getOperations().getExpire(token);

        return times > 0 ? true : false;
    }

    /**
     * 设置键值
     * @param key  键
     * @param value 值
     * @param expire_in 过期时间
     * @param timeUnit 时间单位
     */
    public void set(String key, Object value, long expire_in, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,expire_in,timeUnit);
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public Object get(String key){

        return redisTemplate.opsForValue().get(key);
    }

}
