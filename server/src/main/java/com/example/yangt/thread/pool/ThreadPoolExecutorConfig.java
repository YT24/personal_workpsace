package com.example.yangt.thread.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolExecutorConfig {

 
    @Autowired
    ThreadPoolConfig  threadPoolConfig;
 
    @Bean
    public ThreadPoolExecutor taskExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(),
                threadPoolConfig.getMaxPoolSize(),
                threadPoolConfig.getKeepAliveSeconds(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(threadPoolConfig.getQueueCapacity()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 四种拒绝策略
        //1，AbortPolicy（默认）直接抛出异常 组织系统正常运行
        //2，CallerRunsPolicy 调用者运行一种调节机制 不会抛弃任务也不会抛出异常 而是将某些任务回退到调用者
        //3，DiscardOldestPolicy 抛弃队列中等待最久的任务，然后把当前任务加入到队列中尝试再次提交当前任务
        //4，DiscardPolicy 直接丢弃任务 不报异常 如果允许任务丢失 最优方案
        return threadPoolExecutor;
    }
}