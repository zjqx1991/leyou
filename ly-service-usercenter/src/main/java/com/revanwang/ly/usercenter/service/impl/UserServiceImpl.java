package com.revanwang.ly.usercenter.service.impl;

import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.usercenter.User;
import com.revanwang.ly.usercenter.mapper.IUserMapper;
import com.revanwang.ly.usercenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;

    @Override
    public LYRevanResponse userCenterCheck(String data, Integer type) {
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                RevanThrowException.throwException(RevanResponseCode.PARAM_FAIL);
                break;
        }
        int count = this.userMapper.selectCount(user);
        RevanResponseData<Boolean> response = new RevanResponseData<>();
        response.setData(count == 0);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, response);
    }
}
