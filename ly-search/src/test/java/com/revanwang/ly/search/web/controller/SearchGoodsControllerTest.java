package com.revanwang.ly.search.web.controller;


import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.search.SearchRequest;
import com.revanwang.ly.search.client.ISearchCategoryClient;
import com.revanwang.ly.search.client.ISearchSkuClient;
import com.revanwang.ly.search.client.ISearchSpuClient;
import com.revanwang.ly.search.dao.IGoodsRepository;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchGoodsControllerTest {

    @Autowired
    private ISearchGoodsService goodsService;
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private ISearchSpuClient spuClient;
    @Autowired
    private ISearchSkuClient skuClient;
    @Autowired
    private ISearchCategoryClient categoryClient;


    //测试 categoryClient
    @Test
    public void searchGoodsTest() {
        System.out.println(this.goodsService);
        SearchRequest request = new SearchRequest();
        request.setKey("手机");
//        LYRevanResponse response = this.goodsService.querySearchPage(request);
//        System.out.println(response);
    }

    //测试 categoryClient
    @Test
    public void queryCategoryListNamesByCidsTest() {
        System.out.println(this.categoryClient);
        LYRevanResponse response = this.categoryClient.queryCategoryListNamesByCids(1L, 2L, 3L);
        System.out.println(response);
    }

    // 测试 ISearchSpuClient
    @Test
    public void querySpuByPageTest() {
        System.out.println(this.spuClient);
        LYRevanResponse response = this.spuClient.querySpuByPage(1, 10, true, null);
        System.out.println(response);
    }

    @Test
    public void querySpuDetailByIdTest() {
        System.out.println(this.spuClient);
        LYRevanResponse response = this.spuClient.querySpuDetailByPId(2L);
        System.out.println(response);
    }

    //测试 ISearchSkuClient
    @Test
    public void querySkuBySpuIdTest() {
        System.out.println(this.skuClient);
        LYRevanResponse response = this.skuClient.querySkuBySpuId(2L);
        System.out.println(response);
    }

}