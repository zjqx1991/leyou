package com.revanwang.product.web.controller;

import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Brand;
import com.revanwang.product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping("page")
    public ResponseEntity<LYPageResult<Brand>> queryBrandsBy(
            @RequestParam(value = "page", defaultValue = "1") Long page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        LYPageResult<Brand> pageResult = this.brandService.queryBrandsByPage(page, rows, sortBy, desc, key);

        System.out.println("请求搜索:==" + pageResult.getItems());

        return ResponseEntity.ok(pageResult);
    }

}
