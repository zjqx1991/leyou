package com.revanwang.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class LYPageResult<T> {
    private Long total;         //总条数
    private Long totalPage;     //总页数
    private List<T> items;      //当前页数据

    public LYPageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public LYPageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
