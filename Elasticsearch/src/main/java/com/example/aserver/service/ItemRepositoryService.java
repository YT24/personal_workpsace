package com.example.aserver.service;

import com.example.aserver.entity.es.Item;
import com.example.aserver.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryService {

    /**
     * 文档的 增删改查
     */
    @Autowired
    private ItemRepository itemRepository;


    /**
     * 索引的增删改查及_settings,_mappings
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 创建索引
     */
    public void createIndex(){
        boolean indexExists = elasticsearchTemplate.indexExists(Item.class);
        if(indexExists){
            elasticsearchTemplate.putMapping(Item.class);
        }
    }

    /**
     * 删除索引
     */
    public void deleteIndex(){
        boolean indexExists = elasticsearchTemplate.indexExists(Item.class);
        if(indexExists){
            elasticsearchTemplate.deleteIndex(Item.class);
        }
    }








}
