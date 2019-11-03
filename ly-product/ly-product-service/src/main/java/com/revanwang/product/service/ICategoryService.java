package com.revanwang.product.service;

import com.revanwang.product.domin.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> queryByPid(Long pid);

}
