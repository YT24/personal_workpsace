package com.example.yangt.config.memorycache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CacheConfig {

    private static final int INITIAL_CACHE_SIZE = 50;

    private static final long MAX_CACHE_SIZE = 100_000;

    private static final long EXPIRE_AFTER_WRITE = 60 * 60;

    private  Map<String, String> mapInstance = null;

    private  LoadingCache<String, String> storage = null;


    public CacheConfig() {
        this.storage = Caffeine.newBuilder()
                // TODO 设置缓存初始值
                .initialCapacity(INITIAL_CACHE_SIZE)
                // TODO 设置缓存最大值
                .maximumSize(MAX_CACHE_SIZE)
                // TODO 设置本地缓存过期时间
                .expireAfterWrite(EXPIRE_AFTER_WRITE, TimeUnit.SECONDS)
                .build(s -> {
                    log.error("Load operation of the cache is not supported.");
                    return null;
                });
        // TODO TEST
        this.mapInstance = this.storage.asMap();

    }

    public Map<String, String> getMapInstance() {
        return mapInstance;
    }
}
