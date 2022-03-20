package com.example.yangt.middleWare.zookeeper;//package com.example.demo.zookeeper;

public interface ZkLock {

    void zkLock(String path);

    void zkUnlock(String path);
}
