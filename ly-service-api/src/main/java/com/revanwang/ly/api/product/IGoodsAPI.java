package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "分类接口管理", description = "分类管理接口，提供分类的增、删、改、查")
public interface IGoodsAPI {


    @ApiOperation("分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "页个数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "saleable", value = "排序", required = false, dataType = "boolean"),
            @ApiImplicitParam(name = "key", value = "搜索关键词", required = false, dataType = "string"),
    })
    LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);
}
