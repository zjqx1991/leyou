package com.revanwang.ly.api.product;

import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "sku接口管理", description = "sku管理接口，提供分类的增、删、改、查")
@RequestMapping("/sku")
public interface ISkuAPI {

    @ApiOperation("根据spu商品id查询sku")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "spu商品id", required = true, dataType = "long"),
    })
    @GetMapping("/list")
    LYRevanResponse querySkuBySpuId(@RequestParam("id") Long id);
}
