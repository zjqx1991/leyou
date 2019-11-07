package com.revanwang.product.service;

import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Brand;

import java.util.List;


public interface IBrandService {
    LYPageResult<Brand> queryBrandsByPage(Long page, Integer rows, String sortBy, boolean desc, String key);

    void saveBrand(Brand brand, List<Long> cids);

    /**
     * 查询brand
     * @param id
     * @return
     */
    Brand queryBrandById(Long id);
}
