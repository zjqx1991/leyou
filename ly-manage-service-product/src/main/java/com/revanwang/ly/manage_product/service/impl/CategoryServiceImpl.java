package com.revanwang.ly.manage_product.service.impl;

import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.Category;
import com.revanwang.ly.manage_product.mapper.ICategoryMapper;
import com.revanwang.ly.manage_product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public LYRevanResponse queryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = this.categoryMapper.select(category);
        if (CollectionUtils.isEmpty(list)) {
            RevanThrowException.throwException(RevanResponseCode.CATEGORY_NOT_FOUND);
        }

        RevanResponseData<List<Category>> responseData = new RevanResponseData<>();
        responseData.setData(list);
        responseData.setTotal(Long.valueOf(list.size()));
        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
    }

    @Override
    public LYRevanResponse queryByListPid(List<Long> pids) {
        List<Category> list = this.categoryMapper.selectByIdList(pids);
        if (CollectionUtils.isEmpty(list)) {
            RevanThrowException.throwException(RevanResponseCode.CATEGORY_NOT_FOUND);
        }
        RevanResponseData<List<Category>> responseData = new RevanResponseData<>();
        responseData.setData(list);
        responseData.setTotal(Long.valueOf(list.size()));
        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
    }
}
