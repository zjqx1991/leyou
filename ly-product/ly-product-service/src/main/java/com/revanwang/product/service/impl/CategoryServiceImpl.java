package com.revanwang.product.service.impl;

import com.revanwang.product.domin.Category;
import com.revanwang.product.mapper.ICategoryMapper;
import com.revanwang.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public List<Category> queryByPid(Long pid) {
        if (pid == 0) {
            throw new RuntimeException("pid不能为空");
        }
        Category category = new Category();
        category.setParentId(pid);
        System.out.println("category：==" + category);
        List<Category> list = this.categoryMapper.select(category);
        return list;
    }
}
