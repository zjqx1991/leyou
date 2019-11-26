package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ISpuAPI;
import com.revanwang.ly.manage_product.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpuController implements ISpuAPI {

    @Autowired
    private ISpuService spuService;


    @Override
    public LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        return this.spuService.querySpuByPage(page, rows, saleable, key);
    }

    @Override
    public LYRevanResponse querySpuDetailById(Long id) {
        return this.spuService.querySpuDetailById(id);
    }
}
