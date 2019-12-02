package com.revanwang.ly.usercenter.service;

import com.revanwang.common.model.LYRevanResponse;

public interface IUserService {
    /**
     * 手机号、用户名的唯一性校验
     * @param data 校验数据
     * @param type 1：用户名
     *             2：手机号
     * @return
     */
    LYRevanResponse userCenterCheck(String data, Integer type);
}
