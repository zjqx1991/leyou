package com.revanwang.ly.usercenter.service.impl;

import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.common.utils.NumberUtils;
import com.revanwang.ly.domain.usercenter.User;
import com.revanwang.ly.usercenter.mapper.IUserMapper;
import com.revanwang.ly.usercenter.service.IUserService;
import com.revanwang.ly.usercenter.utils.CodeUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    static final String KEY_PREFIX = "user:sms:code:phone";

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

    @Override
    public LYRevanResponse sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);

        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);

        // 发送消息
        this.amqpTemplate.convertAndSend("sms.verify.code", msg);

        // 保存code到redis中 并设置 5分钟有效
        this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);

        RevanResponseData<Map<String, String>> response = new RevanResponseData<>();
        response.setData(msg);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, response);
    }

    @Override
    public LYRevanResponse register(User user, String code) {
        // 获取key
        String key = KEY_PREFIX + user.getPhone();

        // 从redis中获取验证码
        String cacheCode = this.redisTemplate.opsForValue().get(key);
        // 校验验证码是否正确
        if (!code.equals(cacheCode)) {
            RevanThrowException.throwException(RevanResponseCode.USER_CODE_ERROR);
        }
        user.setId(null);
        user.setCreated(new Date());

        // 生成盐
        String salt = CodeUtils.generateSalt();
        user.setSalt(salt);

        // 密码加密
        user.setPassword(CodeUtils.md5Hex(user.getPassword(), salt));

        Boolean register = this.userMapper.insert(user) == 1;

        //异常redis中缓存
        if (register) {
            this.redisTemplate.delete(key);
        }
        RevanResponseData<Boolean> response = new RevanResponseData<>();
        response.setData(register);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, response);
    }

    @Override
    public LYRevanResponse login(String username, String password) {
        User u = new User();
        u.setUsername(username);
        // 查询用户
        User user = this.userMapper.selectOne(u);

        if (user == null) {
            RevanThrowException.throwException(RevanResponseCode.PARAM_FAIL);
        }

        // 校验密码
        if (!user.getPassword().equals(CodeUtils.md5Hex(password, user.getSalt()))) {
            RevanThrowException.throwException(RevanResponseCode.PARAM_FAIL);
        }

        RevanResponseData<User> response = new RevanResponseData<>();
        response.setData(user);

        return new LYRevanResponse(RevanResponseCode.SUCCESS, response);
    }
}
