package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.IBrandAPI;
import com.revanwang.ly.domain.product.Brand;
import com.revanwang.ly.manage_product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class BrandController implements IBrandAPI {

    @Autowired
    private IBrandService brandService;

    @Override
    public LYRevanResponse findBrandsBy(Long page, Integer rows, String sortBy, boolean desc, String key) {
        return this.brandService.queryBrandsByPage(page, rows, sortBy, desc, key);
    }

    @Override
    public LYRevanResponse saveBrand(Brand brand, List<Long> categories) {
        return this.brandService.saveBrand(brand, categories);
    }

    @Override
    public LYRevanResponse queryBrandListByCid(Long cid) {
        return this.brandService.queryBrandListByCid(cid);
    }

}
