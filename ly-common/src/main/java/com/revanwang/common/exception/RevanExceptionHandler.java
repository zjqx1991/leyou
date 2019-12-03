package com.revanwang.common.exception;

import com.google.common.collect.ImmutableMap;
import com.revanwang.common.model.BaseResponse;
import com.revanwang.common.model.IBaseResponseCode;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一拦击异常类
 */
@ControllerAdvice
public class RevanExceptionHandler {

    /**
     *  使用Exceptions存放异常类和错误代码的映射，
     *  ImmutableMap的特点一旦创建不可改变，并且线程安全
     */
    private static ImmutableMap<Class <? extends Throwable>, IBaseResponseCode> EXCEPTIONS;

    //使用builder来构建一个异常类型和错误代码的异常
    protected static ImmutableMap.Builder<Class<? extends  Throwable>, IBaseResponseCode> builder = ImmutableMap.builder();

    static {
        //在这里加入一些基础异常类型判断
        builder.put(HttpMessageNotReadableException.class, RevanResponseCode.INVALIDPARAM);
    }

    /**
     * 处理自定义异常类
     */
    @ResponseBody
    @ExceptionHandler(value = RevanException.class)
    public LYRevanResponse exceptionHandler(RevanException e) {

        IBaseResponseCode responseCode = e.getResponseCode();
        return new LYRevanResponse(responseCode);
    }

    /**
     * 处理系统异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse systemException(Exception e) {
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        final IBaseResponseCode resultCode = EXCEPTIONS.get(e.getClass());
        final BaseResponse responseResult;
        if (resultCode != null) {
            responseResult = new BaseResponse(resultCode);
        }
        else {
            responseResult = new BaseResponse(RevanResponseCode.SERVER_ERROR);
        }
        return responseResult;
    }
}
