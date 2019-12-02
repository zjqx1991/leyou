package com.revanwang.ly.usercenter.web.controller;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.api.usercenter.IUserAPI;
import com.revanwang.ly.usercenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserAPI {
    @Autowired
    private IUserService userService;

    @Override
    public LYRevanResponse userCenterCheck(@PathVariable("data") String data,
                                    @PathVariable("type") Integer type) {
        return this.userService.userCenterCheck(data, type);
    }
}
