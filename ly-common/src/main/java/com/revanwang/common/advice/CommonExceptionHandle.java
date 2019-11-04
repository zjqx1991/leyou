package com.revanwang.common.advice;

import com.revanwang.common.enums.LYExceptionEnum;
import com.revanwang.common.exception.LYException;
import com.revanwang.common.vo.LYExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理异常
 */
@ControllerAdvice
public class CommonExceptionHandle {

    @ExceptionHandler(LYException.class)
    public ResponseEntity<LYExceptionResult>handleException(LYException e) {
        LYExceptionEnum em = e.getExceptionEnum();
        return ResponseEntity.status(em.getCode()).body(new LYExceptionResult(em));
    }

}
