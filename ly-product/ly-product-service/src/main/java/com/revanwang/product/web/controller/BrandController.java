package com.revanwang.product.web.controller;

import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Brand;
import com.revanwang.product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    /**
     * 查询品牌
     */
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

    /**
     * 新增品牌
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("categories")List<Long> categories) {
        System.out.println("BrandController.saveBrand:==="+ brand + "___" + categories);
        this.brandService.saveBrand(brand, categories);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandListByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(this.brandService.queryBrandListByCid(cid));
    }

}
