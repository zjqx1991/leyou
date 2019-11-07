package com.revanwang.product.web.controller;

import com.revanwang.product.domin.SpecGroup;
import com.revanwang.product.domin.SpecParam;
import com.revanwang.product.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<SpecGroup>> querySpecGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificationService.querySpecGroupByCid(cid));
    }


    /**
     * 通过规格组id来查询对应的规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParamByGId(@RequestParam("gid") Long gid) {
        return ResponseEntity.ok(specificationService.querySpecParamByGId(gid));
    }

}
