package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "分类接口管理", description = "分类管理接口，提供分类的增、删、改、查")
@RequestMapping("/category")
public interface ICategoryAPI {


    @ApiOperation("分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "品牌id", required = true,  paramType = "path", dataType = "long"),
    })
    @GetMapping("/list")
    LYRevanResponse queryCategoryByPid(@RequestParam("pid") Long pid);


    @ApiOperation("通过分类id列表查询分类名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid1", value = "分类id", required = true),
            @ApiImplicitParam(name = "cid2", value = "分类id", required = true),
            @ApiImplicitParam(name = "cid3", value = "分类id", required = true),
    })
    @GetMapping("/names")
    LYRevanResponse queryCategoryListNamesByCids(@RequestParam("cid1") Long cid1,
                                                 @RequestParam("cid2") Long cid2,
                                                 @RequestParam("cid3") Long cid3
                                                 );
}
