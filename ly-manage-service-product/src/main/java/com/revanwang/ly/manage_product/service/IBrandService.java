package com.revanwang.ly.manage_product.service;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.Brand;

import java.util.List;


public interface IBrandService {

    LYRevanResponse queryBrandsByPage(Long page, Integer rows, String sortBy, boolean desc, String key);

    LYRevanResponse saveBrand(Brand brand, List<Long> cids);

    /**
     * 查询品牌
     * @param cid 分类id
     * @return
     */
    LYRevanResponse queryBrandListByCid(Long cid);
}
