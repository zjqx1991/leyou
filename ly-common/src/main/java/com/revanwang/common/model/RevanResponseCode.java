package com.revanwang.common.model;

import lombok.Getter;

@Getter
public enum RevanResponseCode implements IBaseResponseCode {

    SUCCESS(true,0,"操作成功！"),
    FAIL(false,9999,"操作失败！"),
    PARAM_FAIL(false,1, "传入参数有误"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
    INVALIDPARAM(false,99999,"抱歉，系统繁忙，请稍后重试！"),

    //Brand
    BRAND_NOT_FOUND(false,2, "品牌没有查到"),

    //Category
    CATEGORY_NOT_FOUND(false,2, "分类没有查到"),

    //Goods
    GOODS_NOT_FOUND(false,400, "商品没有查到"),

    //SPEC
    CATEGORY_SPEC_GROUP_NOT_FOUND(false,1, "商品分类规格组没有找到"),
    CATEGORY_SPEC_PARAM_NOT_FOUND(false,1, "商品分类规格参数没有找到"),


    //spu
    SPU_NOT_FOUND(false,2, "没有找到spu"),

    //sku
    SKU_NOT_FOUND(false,2, "没有找到sku"),


    PRICE_CANNOT_BE_NULL(false,400, "价格不能为空"),

    INVALID_FILE_TYPE(false,1, "上传文件类型有误"),
    UPLOAD_FILE_FAIL(false,400, "上传文件失败"),


    ;


    private Boolean success;
    private Integer code;
    private String message;

    private RevanResponseCode(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public Boolean success() {
        return this.success;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
