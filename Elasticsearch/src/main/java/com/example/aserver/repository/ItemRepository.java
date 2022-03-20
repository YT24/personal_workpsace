package com.example.aserver.repository;

import com.example.aserver.entity.es.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemRepository extends ElasticsearchCrudRepository<Item,Integer> {

    List<Item> findItemByCategory(String category);
}