package com.revanwang.common.exception;

import com.revanwang.common.model.IBaseResponseCode;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class RevanException extends RuntimeException {
    private IBaseResponseCode responseCode;

    public RevanException(IBaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
