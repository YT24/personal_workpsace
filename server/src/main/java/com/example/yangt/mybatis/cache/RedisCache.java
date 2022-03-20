package com.example.yangt.mybatis.cache;

import com.example.yangt.mybatis.applicationcontext.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class RedisCache implements Cache {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisTemplate redisTemplate;

    //redis过期时间
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    public RedisCache(String id) {
        if (id == null){
            throw  new IllegalArgumentException("Cache instance required an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key.toString(),value,EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        log.info("-------------结果成功放入缓存 and "+"key = " + key + "         value = " + value);
        log.info(""+opsForValue.get(key.toString()));

    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        Object object = redisTemplate.opsForValue().get(key.toString());
        log.info("-------------------------------结果从缓存中获取:"+object);
        return object;
    }

    @Override
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();

        log.info("-------------------------------结果从缓存中删除");
        return redisTemplate.delete(key.toString());
    }

    /**
     * 会清空整个redis数据
     */
    @Override
    public void clear() {
       /* RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });*/
        log.info("清空缓存");

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if(redisTemplate == null){
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
