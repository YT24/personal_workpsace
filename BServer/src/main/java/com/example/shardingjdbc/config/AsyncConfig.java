package com.example.shardingjdbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(16);
        executor.setKeepAliveSeconds(1000);
        executor.setQueueCapacity(Integer.MAX_VALUE);
        executor.setThreadNamePrefix("async-thread-");
        executor.initialize();
        return executor;
    }
}