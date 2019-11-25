package com.revanwang.ly.manage_product.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.product.ISpuAPI;
import com.revanwang.ly.manage_product.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
public class SpuController implements ISpuAPI {

    @Autowired
    private ISpuService spuService;

    @Override
    @GetMapping("/page")
    public LYRevanResponse querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ) {
        return this.spuService.querySpuByPage(page, rows, saleable, key);
    }

    @Override
    @GetMapping("/detail/{id}")
    public LYRevanResponse querySpuDetailById(@PathVariable("id") Long id) {
        return this.spuService.querySpuDetailById(id);
    }

}
