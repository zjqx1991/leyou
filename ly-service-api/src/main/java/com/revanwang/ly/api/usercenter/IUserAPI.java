package com.revanwang.ly.api.usercenter;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.usercenter.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

    /**
     * 发送手机验证码
     * @param phone 手机号
     * @return
     */
    @ApiOperation("发送手机验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "string"),
    })
    @PostMapping("/code")
    LYRevanResponse sendVerifyCode(@RequestParam("phone") String phone);


    /**
     * 用户注册
     * @param user 注册信息
     * @param code 验证码
     * @return
     */
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "user"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "string"),
    })
    @PostMapping("/register")
    LYRevanResponse register(@Valid User user, @RequestParam("code") String code);


    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string"),
    })
    @PostMapping("/login")
    LYRevanResponse login(@RequestParam("username") String username, @RequestParam("password") String password);

}
