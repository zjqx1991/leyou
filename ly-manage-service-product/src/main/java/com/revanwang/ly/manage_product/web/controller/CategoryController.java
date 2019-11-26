package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ICategoryAPI;
import com.revanwang.ly.manage_product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LYRevanResponse queryCategoryListNamesByCids(Long cid1, Long cid2, Long cid3) {
        return this.categoryService.queryCategoryListNamesByCids(cid1, cid2, cid3);
    }

}
