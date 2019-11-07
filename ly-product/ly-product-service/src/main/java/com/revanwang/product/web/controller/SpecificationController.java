package com.revanwang.product.web.controller;

import com.revanwang.product.domin.SpecGroup;
import com.revanwang.product.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private ISpecificationService specificationService;

    /**
     * 通过商品分类查找对应规格组信息
     * @param cid 商品分类
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> specGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificationService.querySpecGroupByCid(cid));
    }


//    public

}
