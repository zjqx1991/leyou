package com.revanwang.ly.manage_product.service;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.SpuBo;

public interface IGoodsService {

    /**
     * 保存商品
     * @param spuBo
     * @return
     */
    LYRevanResponse goodsSave(SpuBo spuBo);
}
