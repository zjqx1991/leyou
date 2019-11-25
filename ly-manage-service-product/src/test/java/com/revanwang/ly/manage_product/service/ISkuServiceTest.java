package com.revanwang.ly.manage_product.service;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.Sku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ISkuServiceTest {

    @Autowired
    private ISkuService skuService;

    @Test
    public void querySkuBySpuId() {
        LYRevanResponse response = this.skuService.querySkuBySpuId(2L);

        List<Sku> data = (List<Sku>) response.getResponseData().getData();
        for (Sku sku: data) {
            System.out.println(sku);
        }
    }
}