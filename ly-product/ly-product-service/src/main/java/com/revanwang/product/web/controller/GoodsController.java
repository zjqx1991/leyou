package com.revanwang.product.web.controller;

import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Spu;
import com.revanwang.product.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("spu/page")
    public ResponseEntity<LYPageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ) {
        return ResponseEntity.ok(this.goodsService.querySpuByPage(page, rows, saleable, key));
    }

}
