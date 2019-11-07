package com.revanwang.product.service;

import com.revanwang.product.domin.Category;

import java.util.List;

public interface ICategoryService {

    /**
     * 通过分类id来查询分类
     * @param pid
     * @return
     */
    List<Category> queryByPid(Long pid);

    /**
     * 通过分类id列表来查询所对应分类
     * @param pids
     * @return
     */
    List<Category> queryByListPid(List<Long> pids);

}
