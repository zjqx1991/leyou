package com.revanwang.ly.search.index;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.utils.JsonUtils;
import com.revanwang.ly.domain.product.Spu;
import com.revanwang.ly.domain.search.Goods;
import com.revanwang.ly.search.LYSearchApplication;
import com.revanwang.ly.search.client.ISearchSpuClient;
import com.revanwang.ly.search.dao.IGoodsRepository;
import com.revanwang.ly.search.service.ISearchGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LYSearchApplication.class)
public class ElasticSearchTest {

    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private ISearchSpuClient spuClient;
    @Autowired
    private ISearchGoodsService goodsService;

    @Test
    public void createIndex() {
        this.esTemplate.createIndex(Goods.class);
        this.esTemplate.putMapping(Goods.class);
    }

    @Test
    public void loadData() {
        int page = 1;
        int rows = 10;
        int size = 0;

        do {

            //创建Goods集合
            List<Goods> goodsList = new ArrayList<>();
            LYRevanResponse response = this.spuClient.querySpuByPage(page, rows, true, null);
            Object data1 = response.getResponseData().getData();
            String jsonString = JSON.toJSONString(data1);

            List<Spu> spus = JSON.parseArray(jsonString, Spu.class);

            for (Spu spu : spus) {
                LYRevanResponse goods = this.goodsService.buildGoods(spu);
                Goods g = (Goods) goods.getResponseData().getData();
                goodsList.add(g);
            }

            //整体保存
            this.goodsRepository.saveAll(goodsList);
            page++;
        }while (size == 100);

    }


}
