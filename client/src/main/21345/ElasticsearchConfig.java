package com.shangjian.indicator.aestest;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.io.IOException;
import java.net.InetAddress;

@Configuration
public class ElasticsearchConfig {
    /**
     * ip地址
     */
    @Value("${test.elasticsearch.ip}")
    private String hostName;

    /**
     * 端口
     */
    @Value("${test.elasticsearch.port}")
    private String port;

    /**
     * 集群名称
     */
    @Value("${test.elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池大小
     */
    @Value("${test.elasticsearch.pool}")
    private String poolSize;

    /**
     * 客户端资源池
     *
     * @return
     */
//    @Bean(name = "transportClient1")
//    public TransportClient transportClient() {
//        TransportClient transportClient = null;
//        try {
//            // 配置信息
//            Settings esSetting = Settings.builder()
//                    //集群名字
//                    .put("cluster.name", clusterName)
//                    //增加嗅探机制，找到ES集群
//                    .put("client.transport.sniff", true)
//                    //增加线程池个数，暂时设为5
//                    .put("thread_pool.search.size", Integer.parseInt(poolSize))
//                    .build();
//            //配置信息Settings自定义
//            transportClient = new PreBuiltTransportClient(esSetting);
//            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port));
//            transportClient.addTransportAddresses(transportAddress);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return transportClient;
//    }

    /**
     * spring对java api的封装
     *
     * @param client 客户端资源池
     * @return
     */
//    @Bean
//    ElasticsearchTemplate elasticsearchTemplate(TransportClient client) {
//        ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client);
//        return elasticsearchTemplate;
//    }
}