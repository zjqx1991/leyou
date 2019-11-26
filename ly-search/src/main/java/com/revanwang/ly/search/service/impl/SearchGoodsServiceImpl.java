package com.revanwang.ly.search.service.impl;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.search.client.ISearchSkuClient;
import com.revanwang.ly.search.client.ISearchSpuClient;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchGoodsServiceImpl implements ISearchGoodsService {

    @Autowired
    private ISearchSpuClient spuClient;
    @Autowired
    private ISearchSkuClient skuClient;

    @Override
    public LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        return this.spuClient.querySpuByPage(page, rows, saleable, key);
    }

    @Override
    public LYRevanResponse querySpuDetailById(Long id) {
        return this.spuClient.querySpuDetailById(id);
    }

    @Override
    public LYRevanResponse querySkuBySpuId(Long id) {
        System.out.println("SearchGoodsServiceImpl.querySkuBySpuId:==" + this.skuClient);
        return this.skuClient.querySkuBySpuId(id);
    }
}
