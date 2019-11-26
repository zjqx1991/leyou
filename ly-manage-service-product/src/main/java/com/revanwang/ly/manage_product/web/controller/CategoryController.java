package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ICategoryAPI;
import com.revanwang.ly.manage_product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class CategoryController implements ICategoryAPI {

    @Autowired
    private ICategoryService categoryService;


    @Override
    public LYRevanResponse queryCategoryByPid(Long pid) {
        return this.categoryService.queryByPid(pid);
    }
}
