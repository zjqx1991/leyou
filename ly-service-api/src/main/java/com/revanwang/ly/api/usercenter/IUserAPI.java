package com.revanwang.ly.api.usercenter;

import com.revanwang.common.model.LYRevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "用户中心接口管理", description = "用户中心管理接口")
@RequestMapping("/usercenter")
public interface IUserAPI {

    /**
     * 手机号、用户名的唯一性校验
     * @param data 校验数据
     * @param type 1：用户名
     *             2：手机号
     * @return
     */
    @ApiOperation("手机号、用户名的唯一性校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "校验值", required = true, dataType = "string"),
            @ApiImplicitParam(name = "type", value = "校验数据类型", required = true, dataType = "int"),
    })
    @GetMapping("/check/{data}/{type}")
    LYRevanResponse userCenterCheck(@PathVariable("data") String data,
                                    @PathVariable("type") Integer type);
}
