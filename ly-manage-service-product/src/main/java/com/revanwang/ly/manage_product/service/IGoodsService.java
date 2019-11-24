package com.revanwang.ly.manage_product.service;

import com.revanwang.common.model.LYRevanResponse;

public interface IGoodsService {
    /**
     * 查询spu
     * @param page 当前页
     * @param rows 每页个数
     * @param saleable 是否可销售
     * @param key 关键词
     * @return
     */
    LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);
}
