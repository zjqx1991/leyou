package com.revanwang.product.service.impl;

import com.revanwang.common.enums.LYExceptionEnum;
import com.revanwang.common.exception.LYException;
import com.revanwang.product.domin.SpecGroup;
import com.revanwang.product.mapper.ISpecGroupMapper;
import com.revanwang.product.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationServiceImpl implements ISpecificationService {

    @Autowired
    private ISpecGroupMapper specGroupMapper;

    @Override
    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> specGroupList = this.specGroupMapper.select(group);
        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new LYException(LYExceptionEnum.CATEGORY_SPEC_GROUP_NOT_FOUND);
        }
        return specGroupList;
    }
}
