package com.revanwang.common.model;

public interface IBaseResponseCode {
    /**
     * 操作是否成功
     * true为成功，
     * false操作失败
     */
    Boolean success();

    /**
     * 响应code
     */
    Integer code();

    /**
     * 响应信息
     */
    String message();
}
