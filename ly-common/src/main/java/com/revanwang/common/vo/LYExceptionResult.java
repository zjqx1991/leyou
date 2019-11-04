package com.revanwang.common.vo;

import com.revanwang.common.enums.LYExceptionEnum;
import lombok.Getter;

@Getter
public class LYExceptionResult {
    private int code;
    private String msg;
    private long timestamp;

    public LYExceptionResult(LYExceptionEnum em) {
        this.code = em.getCode();
        this.msg = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
