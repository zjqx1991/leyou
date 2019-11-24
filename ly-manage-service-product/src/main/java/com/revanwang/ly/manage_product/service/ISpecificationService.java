package com.revanwang.ly.manage_product.service;


import com.revanwang.common.model.LYRevanResponse;

public interface ISpecificationService {
    /**
     * 通过商品分类id获取规格组
     * @param cid 商品分类
     * @return
     */
    LYRevanResponse querySpecGroupByCid(Long cid);

    /**
     * 通过规格组id查询规格组参数
     * @param gid
     * @return
     */
    LYRevanResponse querySpecParamByGId(Long gid);
}
