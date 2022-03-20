package com.example.aserver.utils;

import com.example.aserver.annotation.EsSetting;
import com.example.aserver.constant.CommonConstant;
import com.example.aserver.entity.EsBaseBean;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;

import java.io.IOException;


@Slf4j
public class EsUtil {


    /**
     * 创建 CreateIndexRequest
     * @param clazz
     * @param indexName
     * @return
     * @throws IOException
     */
    private static CreateIndexRequest createIndexRequest(Class<? extends EsBaseBean> clazz, String indexName){
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        buildSetting(request, clazz);
        return request;
    }

    /**
     * 设置分片,副本数量
     * es 默认 5个分片 一个副本
     * @param request
     * @param clazz
     */
    private static void buildSetting(CreateIndexRequest request, Class<? extends EsBaseBean> clazz) {
        final EsSetting annotation = clazz.getAnnotation(EsSetting.class);
        Settings.Builder builder = Settings.builder();
        boolean isSetting = false;
        if (annotation.shards() > 0) {
            builder.put(CommonConstant.NUMBER_OF_SHARDS, annotation.shards());
            isSetting = true;
        }
        if (annotation.replicas() > 0) {
            builder.put(CommonConstant.NUMBER_OF_REPLICAS, annotation.replicas());
            isSetting = true;
        }
        if (isSetting) {
            request.settings(builder);
        }
    }





}