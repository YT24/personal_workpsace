package com.example.yangt.config.zookeeper;//package com.example.demo.config.zookeeper;
//
//import org.I0Itec.zkclient.ZkClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ZookeeperConfig {
//
//    @Value("${spring.zookeeper.timed}")
//    private String TIME_OUT;
//
//    @Value("${spring.zookeeper.host}")
//    private String host;
//
//    @Bean
//    public ZkClient zkClient(){
//        ZkClient zkClient = new ZkClient(host,Integer.parseInt(TIME_OUT));
//
//        return zkClient;
//    }
//}
