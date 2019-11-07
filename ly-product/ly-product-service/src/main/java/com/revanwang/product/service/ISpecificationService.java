package com.revanwang.product.service;

import com.revanwang.product.domin.SpecGroup;
import com.revanwang.product.domin.SpecParam;

import java.util.List;

public interface ISpecificationService {
    /**
     * 通过商品分类id获取规格组
     * @param cid 商品分类
     * @return
     */
    List<SpecGroup> querySpecGroupByCid(Long cid);

    /**
     * 通过规格组id查询规格组参数
     * @param gid
     * @return
     */
    List<SpecParam> querySpecParamByGId(Long gid);
}
