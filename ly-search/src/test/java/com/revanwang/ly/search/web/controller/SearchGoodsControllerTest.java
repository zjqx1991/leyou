package com.revanwang.ly.search.web.controller;


import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchGoodsControllerTest {

    @Autowired
    private ISearchGoodsService goodsService;

    @Test
    public void querySpuByPage() {
        LYRevanResponse response = this.goodsService.querySpuByPage(1, 10, false, "");
        Object data = response.getResponseData().getData();
    }

    @Test
    public void querySpuDetailById() {
        LYRevanResponse response = this.goodsService.querySpuDetailById(2L);
        Object data = response.getResponseData().getData();
    }

    @Test
    public void querySkuBySpuId() {
        LYRevanResponse response = this.goodsService.querySkuBySpuId(2L);
        Object data = response.getResponseData().getData();
    }
}