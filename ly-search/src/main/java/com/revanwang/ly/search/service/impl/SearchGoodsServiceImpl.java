package com.revanwang.ly.search.service.impl;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.search.client.ISearchGoodsClient;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchGoodsServiceImpl implements ISearchGoodsService {

    @Autowired
    private ISearchGoodsClient searchGoodsClient;

    @Override
    public LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        return this.searchGoodsClient.querySpuByPage(page, rows, saleable, key);
    }

    @Override
    public LYRevanResponse querySpuDetailById(Long id) {
        return this.searchGoodsClient.querySpuDetailById(id);
    }

    @Override
    public LYRevanResponse querySkuBySpuId(Long id) {
        System.out.println("SearchGoodsServiceImpl.querySkuBySpuId:==" + this.searchGoodsClient);
        return this.searchGoodsClient.querySkuBySpuId(id);
    }
}
