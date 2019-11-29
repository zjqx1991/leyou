package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "spu接口管理", description = "spu管理接口，提供分类的增、删、改、查")
@RequestMapping("/spu")
public interface ISpuAPI {


    @ApiOperation("分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "页个数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "saleable", value = "排序", required = false, dataType = "boolean"),
            @ApiImplicitParam(name = "key", value = "搜索关键词", required = false, dataType = "string"),
    })
    @GetMapping("/page")
    LYRevanResponse querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    );

    @ApiOperation("根据spu商品id查询详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "spu商品id", required = true, dataType = "long"),
    })
    @GetMapping("/detail")
    LYRevanResponse querySpuDetailByPId(@RequestParam(value = "id") Long id);


    @ApiOperation("根据spu商品id查询spu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "spu商品id", required = true, dataType = "long"),
    })
    @GetMapping("/{id}")
    LYRevanResponse querySpuById(@PathVariable("id") Long id);

}
