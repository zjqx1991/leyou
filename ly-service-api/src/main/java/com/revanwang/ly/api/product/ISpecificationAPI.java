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


@Api(value = "spec接口管理", description = "spec管理接口，提供分类的增、删、改、查")
@RequestMapping("/spec")
public interface ISpecificationAPI {


    @ApiOperation("通过商品分类查找对应规格组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "分类id", required = true, dataType = "long"),
    })
    @GetMapping("/groups/{cid}")
    LYRevanResponse querySpecGroupByCid(@PathVariable("cid") Long cid);


    @ApiOperation("通过分类id 或 规格组id 来查询对应的规格参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "分类id", required = false, dataType = "long"),
            @ApiImplicitParam(name = "gid", value = "参数规格组id", required = false, dataType = "long"),
    })
    @GetMapping("/params")
    LYRevanResponse querySpecParamByIds(@RequestParam("cid") Long cid,
                                        @RequestParam("gid") Long gid);

}
