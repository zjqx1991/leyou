package com.revanwang.ly.api.goods_page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "商品接口管理", description = "商品管理接口")
@RequestMapping("/item")
public interface IGoodsPageAPI {

    @ApiOperation("商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "spuid", required = true, dataType = "long"),
    })
    @GetMapping("/{id}.html")
    String toItemPage(Model model, @PathVariable("id") Long id);
}
