package com.revanwang.ly.api.product;


import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(value = "分类接口管理", description = "分类管理接口，提供分类的增、删、改、查")
public interface ISpecificationAPI {


    @ApiOperation("通过商品分类查找对应规格组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "分类id", required = true, dataType = "long"),
    })
    LYRevanResponse querySpecGroupByCid(Long cid);


    @ApiOperation("通过规格组id来查询对应的规格参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gid", value = "规格id", required = true, dataType = "long"),
    })
    LYRevanResponse querySpecParamByGId(Long gid);
}
