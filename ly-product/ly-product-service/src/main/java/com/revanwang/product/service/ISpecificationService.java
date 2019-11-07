package com.revanwang.product.service;

import com.revanwang.product.domin.SpecGroup;

import java.util.List;

public interface ISpecificationService {
    /**
     * 通过商品分类id获取规格组
     * @param cid 商品分类
     * @return
     */
    List<SpecGroup> querySpecGroupByCid(Long cid);
}
