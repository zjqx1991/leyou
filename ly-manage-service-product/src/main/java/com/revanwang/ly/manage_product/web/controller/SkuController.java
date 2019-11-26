package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ISkuAPI;
import com.revanwang.ly.manage_product.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SkuController implements ISkuAPI {

    @Autowired
    private ISkuService skuService;

    @Override
    public LYRevanResponse querySkuBySpuId(Long id) {
        return this.skuService.querySkuBySpuId(id);
    }
}
