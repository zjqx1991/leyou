package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ICategoryAPI;
import com.revanwang.ly.manage_product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("category")
public class CategoryController implements ICategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @Override
    @GetMapping("list")
    public LYRevanResponse queryCategoryByPid(@RequestParam("pid") Long pid) {
        return this.categoryService.queryByPid(pid);
    }
}
