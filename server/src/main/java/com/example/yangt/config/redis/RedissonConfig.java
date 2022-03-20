package com.example.yangt.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;


    /**
     * @return 配置好的redisson
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        /*
         * 单机
         */
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        /*
         * 哨兵
         */
        //config.useSentinelServers().addSentinelAddress("redis://" + host + ":" + port).setMasterName("admin").setPassword("password");
        return Redisson.create(config);
    }

    @Bean
    public DefaultRedisScript<List> defaultRedisScript() {
        DefaultRedisScript<List> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(List.class);
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new
                ClassPathResource("lua/unlock.lua")));
        return defaultRedisScript;
    }
}
