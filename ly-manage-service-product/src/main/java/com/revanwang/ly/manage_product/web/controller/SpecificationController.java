package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ISpecificationAPI;
import com.revanwang.ly.manage_product.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SpecificationController implements ISpecificationAPI {

    @Autowired
    private ISpecificationService specificationService;



    /**
     * 通过商品分类查找对应规格组信息
     * @param cid 商品分类
     * @return
     */
    public LYRevanResponse querySpecGroupByCid(Long cid) {
        return this.specificationService.querySpecGroupByCid(cid);
    }

    /**
     * "通过分类id 或 规格组id 来查询对应的规格参数"
     * @param cid 分类id
     * @param gid 规格组id
     * @return
     */
    @Override
    public LYRevanResponse querySpecParamByIds(@RequestParam(value = "cid", defaultValue = "0") Long cid,
                                               @RequestParam(value = "gid", defaultValue = "0") Long gid) {
        return this.specificationService.querySpecParamByIds(cid, gid);
    }

}
