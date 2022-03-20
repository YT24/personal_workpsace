package com.example.yangt.middleWare.zookeeper;//package com.example.demo.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ZkDistributeLock extends ZkAbstractTemplateLock{
    @Override
    public boolean tryZkLock(String path) {
        try {
            zkClient.createEphemeral(path);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void waitZkLock(String path) {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(path,iZkDataListener);
        if(zkClient.exists(path)){
         //如果存在 则等着
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(path,iZkDataListener);
    }
}
