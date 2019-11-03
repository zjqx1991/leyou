package com.revanwang.product.web.controller;

import com.revanwang.product.domin.Category;
import com.revanwang.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam("pid") Long pid) {
        System.out.println("CategoryController.queryCategoryByPid:===" + pid);
        List<Category> list = this.categoryService.queryByPid(pid);
        return ResponseEntity.ok(list);
    }

}
