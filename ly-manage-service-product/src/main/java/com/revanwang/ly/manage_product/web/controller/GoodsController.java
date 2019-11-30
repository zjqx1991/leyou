package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.IGoodsAPI;
import com.revanwang.ly.domain.product.SpuBo;
import com.revanwang.ly.manage_product.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GoodsController implements IGoodsAPI {

    @Autowired
    private IGoodsService goodsService;

    @Override
    public LYRevanResponse goodsSave(@RequestBody SpuBo spuBo) {
        return this.goodsService.goodsSave(spuBo);
    }
}
