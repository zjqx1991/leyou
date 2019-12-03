package com.revanwang.ly.usercenter.service;

import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.ly.domain.usercenter.User;

public interface IUserService {
    /**
     * 手机号、用户名的唯一性校验
     * @param data 校验数据
     * @param type 1：用户名
     *             2：手机号
     * @return
     */
    LYRevanResponse userCenterCheck(String data, Integer type);

    /**
     * 发送手机验证码
     * @param phone 手机号
     * @return
     */
    LYRevanResponse sendVerifyCode(String phone);

    /**
     * 用户注册
     * @param user 注册信息
     * @param code 验证码
     * @return
     */
    LYRevanResponse register(User user, String code);


    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    LYRevanResponse login(String username, String password);
}
