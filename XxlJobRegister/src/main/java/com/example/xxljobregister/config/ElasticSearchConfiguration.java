//package com.example.xxljobregister.config;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.AbstractFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticSearchConfiguration {
//
//    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchConfiguration.class);
//
//    @Value("${elasticsearch.host}")
//    private String host;
//
//    @Value("${elasticsearch.port}")
//    private Integer port;
//
//    @Bean
//    public RestHighLevelClient createInstance()  {
//        try {
//            // 如果有多个节点，构建多个HttpHost
//            return new RestHighLevelClient(
//                    RestClient.builder(
//                            new HttpHost(host, port, "http")));
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            throw new RuntimeException("RestHighLevelClient 初始化配置失败！！！");
//        }
//    }
//}