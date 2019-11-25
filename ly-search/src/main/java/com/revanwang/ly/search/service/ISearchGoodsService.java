package com.revanwang.ly.search.service;

import com.revanwang.common.model.LYRevanResponse;

public interface ISearchGoodsService {

    /**
     * 分页查询商品
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);

    /**
     * 根据spu商品id查询详情
     * @param id
     * @return
     */
    LYRevanResponse querySpuDetailById(Long id);

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    LYRevanResponse querySkuBySpuId(Long id);
}
