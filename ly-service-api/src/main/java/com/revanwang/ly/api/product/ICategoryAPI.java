package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "分类接口管理", description = "分类管理接口，提供分类的增、删、改、查")
public interface ICategoryAPI {


    @ApiOperation("分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "品牌id", required = true,  paramType = "path", dataType = "long"),
    })
    LYRevanResponse queryCategoryByPid(Long pid);
}
