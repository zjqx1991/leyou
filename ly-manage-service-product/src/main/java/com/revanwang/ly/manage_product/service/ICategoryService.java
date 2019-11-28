package com.revanwang.ly.manage_product.service;


import com.revanwang.common.model.LYRevanResponse;

import java.util.List;

public interface ICategoryService {

    /**
     * 通过分类id来查询分类
     * @param pid
     * @return
     */
    LYRevanResponse queryByPid(Long pid);

    /**
     * 通过分类id列表来查询所对应分类
     * @param pids
     * @return
     */
    LYRevanResponse queryByListPid(List<Long> pids);

    /**
     * 通过分类ids获取分类名字列表
     * @param cids
     * @return
     */
    LYRevanResponse queryCategoryNamesByCids(List<Long> cids);
}
