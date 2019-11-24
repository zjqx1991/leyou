package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.IBrandAPI;
import com.revanwang.ly.domain.product.Brand;
import com.revanwang.ly.manage_product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("brand")
public class BrandController implements IBrandAPI {

    @Autowired
    private IBrandService brandService;



    /**
     * 查询品牌
     */
    @Override
    @GetMapping("page")
    public LYRevanResponse findBrandsBy(
            @RequestParam(value = "page", defaultValue = "1") Long page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        return this.brandService.queryBrandsByPage(page, rows, sortBy, desc, key);
    }

    /**
     * 新增品牌
     */
    @Override
    @PostMapping
    public LYRevanResponse saveBrand(Brand brand, @RequestParam("categories")List<Long> categories) {
        return this.brandService.saveBrand(brand, categories);
    }

    @GetMapping("cid/{cid}")
    public LYRevanResponse queryBrandListByCid(@PathVariable("cid") Long cid) {
        return this.brandService.queryBrandListByCid(cid);
    }

}
