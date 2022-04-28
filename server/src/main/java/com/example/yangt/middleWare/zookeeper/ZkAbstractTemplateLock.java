package com.example.yangt.middleWare.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 模板模式
 */
@Slf4j
@Component
public abstract class ZkAbstractTemplateLock implements ZkLock{

    //@Autowired
    protected ZkClient zkClient;

    protected CountDownLatch countDownLatch = null;


    @Override
    public void zkLock(String path){
        if(tryZkLock(path)){
            log.info(Thread.currentThread().getName()+"\t 站用锁成功");
        }else{
            waitZkLock(path);
            zkLock(path);
            log.info("");
        }
    };

    @Override
    public void zkUnlock(String path){

        if(zkClient != null){
            zkClient.close();
        }
        log.info(Thread.currentThread().getName()+"\t 释放锁成功");

    };


    public abstract boolean tryZkLock(String path);

    public abstract void waitZkLock(String path);


}
