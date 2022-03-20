package com.example.xxljobregister.controller;

import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class EsController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @ApiOperation(value = "目录查询")
    @GetMapping("es")
    public void es(HttpServletRequest request){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("index-name-001");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", "kobe"));
        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
        }
        SearchHit[] hits = searchResponse.getHits().getHits();


        DeleteByQueryRequest delete = new DeleteByQueryRequest();
        delete.indices("index-name-001");
        delete.setQuery(QueryBuilders.termQuery("name", "kobe"));
        delete.setRefresh(true);

    }
}
