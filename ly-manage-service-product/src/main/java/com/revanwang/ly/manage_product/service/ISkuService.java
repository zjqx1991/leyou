package com.revanwang.ly.manage_product.service;

import com.revanwang.common.model.LYRevanResponse;

public interface ISkuService {

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    LYRevanResponse querySkuBySpuId(Long id);
}
