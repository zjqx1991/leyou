package com.revanwang.ly.manage_product.service;

import com.revanwang.common.model.LYRevanResponse;

public interface ISpuService {
    /**
     * 查询spu
     * @param page 当前页
     * @param rows 每页个数
     * @param saleable 是否可销售
     * @param key 关键词
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
     * 根据spu商品id查询spu
     * @param id
     * @return
     */
    LYRevanResponse querySpuById(Long id);
}
