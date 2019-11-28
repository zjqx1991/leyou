package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ICategoryAPI;
import com.revanwang.ly.manage_product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

public class CategoryController implements ICategoryAPI {

    @Autowired
    private ICategoryService categoryService;


    @Override
    public LYRevanResponse queryCategoryByPid(Long pid) {
        return this.categoryService.queryByPid(pid);
    }

    @Override
    public LYRevanResponse queryCategoryNamesByCids(@RequestParam("cids") List<Long> cids) {
        return this.categoryService.queryCategoryNamesByCids(cids);
    }

}
