package com.shangjian.indicator.aestest;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemRepository extends ElasticsearchRepository<Item,Integer> {

    List<Item> findItemByCategory(String category);
}