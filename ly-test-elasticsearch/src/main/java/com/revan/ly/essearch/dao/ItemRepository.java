package com.revan.ly.essearch.dao;

import com.revan.ly.essearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
}
