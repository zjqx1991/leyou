package com.revanwang.common.exception;

import com.revanwang.common.enums.LYExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LYException extends RuntimeException {
    private LYExceptionEnum exceptionEnum;
}
