package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.manage_product.service.ISpuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpuControllerTest {

    @Autowired
    private ISpuService spuService;

    @Test
    public void querySpuByPage() {
    }

    @Test
    public void querySpuDetailById() {
        LYRevanResponse response = spuService.querySpuDetailById(2L);
        Object data = response.getResponseData().getData();

    }
}