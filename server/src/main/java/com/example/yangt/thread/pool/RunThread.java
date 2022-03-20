package com.example.yangt.thread.pool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunThread implements Runnable {
    @Override
    public void run() {

        log.info("线程名称："+Thread.currentThread().getName()+"  线程id："+Thread.currentThread().getId());
    }
}
