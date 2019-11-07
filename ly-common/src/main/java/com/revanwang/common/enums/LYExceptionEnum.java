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
    BRAND_NOT_FOUND(400, "品牌没有查到"),
    INVALID_FILE_TYPE(1, "上传文件类型有误"),
    UPLOAD_FILE_FAIL(400, "上传文件失败"),
    CATEGORY_SPEC_GROUP_NOT_FOUND(1, "商品分类规格组没有找到"),
    ;
    private int code;
    private String msg;
}
