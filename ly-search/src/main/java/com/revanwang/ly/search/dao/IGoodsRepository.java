package com.revanwang.ly.search.dao;

import com.revanwang.ly.domain.search.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IGoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
