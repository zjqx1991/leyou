package com.revanwang.product.service.impl;

import com.revanwang.common.enums.LYExceptionEnum;
import com.revanwang.common.exception.LYException;
import com.revanwang.product.domin.Category;
import com.revanwang.product.mapper.ICategoryMapper;
import com.revanwang.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public List<Category> queryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        System.out.println("category：==" + category);
        List<Category> list = this.categoryMapper.select(category);
        if (CollectionUtils.isEmpty(list)) {
            throw new LYException(LYExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    @Override
    public List<Category> queryByListPid(List<Long> pids) {
        List<Category> list = this.categoryMapper.selectByIdList(pids);
        if (CollectionUtils.isEmpty(list)) {
            throw new LYException(LYExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
