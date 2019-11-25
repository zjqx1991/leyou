package com.revanwang.ly.search.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ISearchGoodsAPI;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search/goods")
public class SearchGoodsController implements ISearchGoodsAPI {

    @Autowired
    private ISearchGoodsService searchGoodsService;

    @Override
    public LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        return this.searchGoodsService.querySpuByPage(page, rows, saleable, key);
    }

    @Override
    public LYRevanResponse querySpuDetailById(Long id) {
        return this.searchGoodsService.querySpuDetailById(id);
    }

    @Override
    public LYRevanResponse querySkuBySpuId(Long id) {
        return this.searchGoodsService.querySkuBySpuId(id);
    }
}
