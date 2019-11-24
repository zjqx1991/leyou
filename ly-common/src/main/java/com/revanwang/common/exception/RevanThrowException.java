package com.revanwang.common.exception;


import com.revanwang.common.model.IBaseResponseCode;

/**
 * 异常抛出类
 */
public class RevanThrowException {

    /**
     * 抛出自定义异常
     */
    public static void throwException(IBaseResponseCode resultCode) {
        throw new RevanException(resultCode);
    }

}
