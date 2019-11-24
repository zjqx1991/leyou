package com.revanwang.ly.api.product;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.Brand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "品牌(Brand)接口管理", description = "品牌(Brand)管理接口，提供品牌(Brand)的增、删、改、查")
public interface IBrandAPI {

    @ApiOperation("查询品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "long"),
            @ApiImplicitParam(name = "rows", value = "页个数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sortBy", value = "排序", required = false, dataType = "string"),
            @ApiImplicitParam(name = "desc", value = "降序", required = false, dataType = "boolean"),
            @ApiImplicitParam(name = "key", value = "搜索关键词", required = false, dataType = "string"),
    })
    LYRevanResponse findBrandsBy(Long page, Integer rows,
                                 String sortBy, boolean desc, String key);

    @ApiOperation("新增品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand", value = "品牌", required = true, dataType = "Brand"),
            @ApiImplicitParam(name = "categories", value = "品牌ids", required = true, dataType = "List"),
    })
    LYRevanResponse saveBrand(Brand brand, List<Long> categories);

    @ApiOperation("查询品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "分类id", required = true, paramType = "path", dataType = "long"),
    })
    LYRevanResponse queryBrandListByCid(Long cid);

}
