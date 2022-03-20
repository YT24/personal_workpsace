package com.example.aserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @description 基础包的注释驱动配置，配置自动扫描的repositories根目录
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.aserver.repository")
public interface ESConfig {
}