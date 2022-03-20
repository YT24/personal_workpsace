package com.example.aserver.entity.es;

import com.example.aserver.entity.EsBaseBean;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "item-index",shards = 5,replicas = 1,type = "item-type",createIndex = true,indexStoreType = "fs")
public class Item extends EsBaseBean {


    @Field(type = FieldType.Text)
    private String title; //标题

    @Field(type = FieldType.Keyword)
    private String category;// 分类

    @Field(type = FieldType.Keyword)
    private String brand; // 品牌

    @Field(type = FieldType.Keyword)
    private Double price; // 价格

    @Field(type = FieldType.Keyword)
    private String images; // 图片地址

    @Field(type = FieldType.Keyword)
    private String searchWord;

    @Field(type = FieldType.Boolean)
    private Boolean delete = false;


}