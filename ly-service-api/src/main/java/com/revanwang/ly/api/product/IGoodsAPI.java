package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.SpuBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value = "Goods接口管理", description = "Goods管理接口，提供分类的增、删、改、查")
@RequestMapping("/goods")
public interface IGoodsAPI {


    @ApiOperation("保存Goods")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SpuBo", value = "商品", required = true, dataType = "SpuBo"),
    })
    @PostMapping("/save")
    LYRevanResponse goodsSave(@RequestBody SpuBo spuBo);

}
