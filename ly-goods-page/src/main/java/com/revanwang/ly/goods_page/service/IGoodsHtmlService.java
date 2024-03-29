package com.revanwang.ly.goods_page.service;

/**
 * @Author: 98050
 * @Time: 2018-10-19 09:40
 * @Feature: 页面详情静态化接口
 */
public interface IGoodsHtmlService {

    /**
     * 创建html页面
     * @param spuId
     */
    void createHtml(Long spuId);

    /**
     * 新建线程处理页面静态化，Controller调用
     * @param spuId
     */
    void asyncExecute(Long spuId);

    /**
     * 删除html页面
     * @param id
     */
    void deleteHtml(Long id);
}
