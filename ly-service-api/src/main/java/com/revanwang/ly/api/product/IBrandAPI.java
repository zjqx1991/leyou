package com.revanwang.ly.api.product;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.product.Brand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "品牌(Brand)接口管理", description = "品牌(Brand)管理接口，提供品牌(Brand)的增、删、改、查")
@RequestMapping("/brand")
public interface IBrandAPI {

    @ApiOperation("查询品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "long"),
            @ApiImplicitParam(name = "rows", value = "页个数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "sortBy", value = "排序", required = false, dataType = "string"),
            @ApiImplicitParam(name = "desc", value = "降序", required = false, dataType = "boolean"),
            @ApiImplicitParam(name = "key", value = "搜索关键词", required = false, dataType = "string"),
    })
    @GetMapping("/page")
    LYRevanResponse findBrandsBy(
            @RequestParam(value = "page", defaultValue = "1") Long page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key
    );

    @ApiOperation("新增品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand", value = "品牌", required = true, dataType = "Brand"),
            @ApiImplicitParam(name = "categories", value = "品牌ids", required = true, dataType = "List"),
    })
    @PostMapping
    LYRevanResponse saveBrand(Brand brand, @RequestParam("categories")List<Long> categories);

    @ApiOperation("查询品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "分类id", required = true, paramType = "path", dataType = "long"),
    })
    @GetMapping("cid/{cid}")
    LYRevanResponse queryBrandListByCid(@PathVariable("cid") Long cid);


    @ApiOperation("通过分类id列表查询品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cids", value = "分类列表", required = true),
    })
    @GetMapping("/list")
    LYRevanResponse queryBrandByIds(@RequestParam("cids") List<Long> cids);

}
