package com.revanwang.common.model;

import lombok.Data;

@Data
public class RevanResponseData<T> {
    private T data;
    private Long total;
}
