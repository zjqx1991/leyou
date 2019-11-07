package com.revanwang.product.service;

import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Spu;

public interface IGoodsService {
    LYPageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);
}
