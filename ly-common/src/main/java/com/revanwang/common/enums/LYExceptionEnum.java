package com.revanwang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常枚举
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LYExceptionEnum {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    BRAND_NOT_FOUND(400, "品牌没有查到")
    ;
    private int code;
    private String msg;
}
