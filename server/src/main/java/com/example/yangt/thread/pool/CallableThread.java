package com.example.yangt.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class CallableThread implements Callable<String> {

    @Override
    public String call() throws Exception {

        log.info(".........:"+Thread.currentThread().getName());
        return "Success";
    }
}
