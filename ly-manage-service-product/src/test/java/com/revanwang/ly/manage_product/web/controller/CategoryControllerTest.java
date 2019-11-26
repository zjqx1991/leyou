package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.manage_product.service.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void queryCategoryListNamesByCids() {
        LYRevanResponse lyRevanResponse = this.categoryService.queryCategoryListNamesByCids(1L, 2L, 3L);
        Object data = lyRevanResponse.getResponseData().getData();
        System.out.println(data);
    }
}