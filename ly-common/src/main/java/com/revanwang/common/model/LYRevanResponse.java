package com.revanwang.common.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LYRevanResponse extends BaseResponse {

    private RevanResponseData responseData;

    public LYRevanResponse() {}

    public LYRevanResponse(IBaseResponseCode responseCode) {
        super(responseCode);
    }

    public LYRevanResponse(IBaseResponseCode responseCode, RevanResponseData responseData) {
        super(responseCode);
        this.responseData = responseData;
    }
}
