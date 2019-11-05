package com.revanwang.product.service;

import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Brand;


public interface IBrandService {
    LYPageResult<Brand> queryBrandsByPage(Long page, Integer rows, String sortBy, boolean desc, String key);
}
