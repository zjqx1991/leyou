package com.revanwang.common.model;

import lombok.Data;

@Data
public class BaseResponse implements IBaseResponse {
    //操作是否成功
    private Boolean success = SUCCESS;

    //操作代码
    private Integer code = SUCCESS_CODE;

    //提示信息
    private String message;

    public BaseResponse(){}

    public BaseResponse(IBaseResponseCode response){
        this.success = response.success();
        this.code = response.code();
        this.message = response.message();
    }

    public static BaseResponse SUCCESS(){
        return new BaseResponse(RevanResponseCode.SUCCESS);
    }
    public static BaseResponse FAIL(){
        return new BaseResponse(RevanResponseCode.FAIL);
    }
}
