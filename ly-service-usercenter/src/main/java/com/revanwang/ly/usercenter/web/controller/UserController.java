package com.revanwang.ly.usercenter.web.controller;

import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.ly.api.usercenter.IUserAPI;
import com.revanwang.ly.domain.usercenter.User;
import com.revanwang.ly.usercenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController implements IUserAPI {
    @Autowired
    private IUserService userService;

    @Override
    public LYRevanResponse userCenterCheck(@PathVariable("data") String data,
                                    @PathVariable("type") Integer type) {
        return this.userService.userCenterCheck(data, type);
    }

    @Override
    public LYRevanResponse sendVerifyCode(@RequestParam("phone") String phone) {
        return this.userService.sendVerifyCode(phone);
    }

    @Override
    public LYRevanResponse register(@Valid User user, @RequestParam("code") String code) {
        return this.userService.register(user, code);
    }

    @Override
    public LYRevanResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return this.userService.login(username, password);
    }
}
