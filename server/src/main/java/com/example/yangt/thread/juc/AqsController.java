package com.example.yangt.thread.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
public class AqsController {


    private Lock lock = new ReentrantLock();

    @RequestMapping("lock1")
    public void lock1(){

        log.info("lock 1 >> >> 线程 >> >> >> {} 开始 lock",Thread.currentThread().getName());
        this.lock.lock();
        this.lock.unlock();
    }

    @RequestMapping("lock2")
    public void lock2(){

        log.info("lock 2 >> >> 线程 >> >> >> {} 开始 lock",Thread.currentThread().getName());
        this.lock.lock();
    }

    @RequestMapping("lock3")
    public void lock3(){

        log.info("lock 3 >> >> 线程 >> >> >> {} 开始 lock",Thread.currentThread().getName());
        this.lock.lock();
    }

    @RequestMapping("unlock")
    public void unlock(){
        Lock lock = new ReentrantLock();
        log.info("线程 >> >> >> {} 开始 unlock",Thread.currentThread().getName());
        lock.lock();
    }
}
