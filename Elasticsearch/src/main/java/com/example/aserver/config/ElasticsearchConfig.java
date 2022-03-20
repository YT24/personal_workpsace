package com.example.aserver.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ElasticsearchConfig.class)
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchConfig {

}
