package com.revanwang.ly.domain.search;


import java.util.Map;

/**
 * 商品页面搜索条件
 */
public class SearchRequest {
    /**
     * 每页大小，不从页面接收，而是固定大小
     */
    public static final Integer DEFAULT_SIZE = 20;// 每页大小，不从页面接收，而是固定大小
    /**
     * 默认页
     */
    private static final Integer DEFAULT_PAGE = 1;// 默认页

    /**
     * 搜索条件
     */
    private String key;
    /**
     * 当前页
     */
    private Integer page = 1;
    /**
     * 排序字段
     */
    private String sortBy;
    /**
     * 是否降序
     */
    private Boolean descending;
    /**
     * 过滤字段
     */
    private Map<String,String> filter;


    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if (page == null){
            return DEFAULT_PAGE;
        }
        // 获取页码时做一些校验，不能小于1
        return Math.max(DEFAULT_PAGE,page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }
}
