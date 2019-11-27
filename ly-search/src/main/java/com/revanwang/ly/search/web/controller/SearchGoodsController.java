package com.revanwang.ly.search.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.search.ISearchGoodsAPI;
import com.revanwang.ly.domain.search.SearchRequest;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchGoodsController implements ISearchGoodsAPI {

    @Autowired
    private ISearchGoodsService searchGoodsService;

    public LYRevanResponse querySearchPage(@RequestBody SearchRequest pageSearch) {
        return this.searchGoodsService.querySearchPage(pageSearch);
    }
}
