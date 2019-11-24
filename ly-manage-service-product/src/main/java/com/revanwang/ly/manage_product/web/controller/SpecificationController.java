package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ISpecificationAPI;
import com.revanwang.ly.manage_product.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("spec")
public class SpecificationController implements ISpecificationAPI {

    @Autowired
    private ISpecificationService specificationService;

    /**
     * 通过商品分类查找对应规格组信息
     * @param cid 商品分类
     * @return
     */
    @GetMapping("groups/{cid}")
    public LYRevanResponse querySpecGroupByCid(@PathVariable("cid") Long cid) {
        return this.specificationService.querySpecGroupByCid(cid);
    }


    /**
     * 通过规格组id来查询对应的规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public LYRevanResponse querySpecParamByGId(@RequestParam("gid") Long gid) {
        return this.specificationService.querySpecParamByGId(gid);
    }

}
