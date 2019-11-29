package com.revanwang.ly.goods_page.service;

import java.util.Map;

public interface IGoodsPageService {

    /**
     * 加载页面数据
     * @param id
     * @return
     */
    Map<String, Object> loadModel(Long id);
}
