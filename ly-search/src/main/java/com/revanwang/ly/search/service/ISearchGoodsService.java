package com.revanwang.ly.search.service;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.Spu;
import com.revanwang.ly.domain.search.SearchRequest;


public interface ISearchGoodsService {


    /**
     * 根据goods的id创建相应的索引
     * @param id
     */
    void createIndex(Long id);

    /**
     * 通过spu来构建Goods的搜索库
     * @param spu
     * @return
     */
    LYRevanResponse buildGoods(Spu spu);

    /**
     * 搜索商品页面
     * @param pageSearch
     * @return
     */
    LYRevanResponse querySearchPage(SearchRequest pageSearch);
}
