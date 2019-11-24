package com.revanwang.ly.manage_product.service.impl;

import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.SpecGroup;
import com.revanwang.ly.domain.product.SpecParam;
import com.revanwang.ly.manage_product.mapper.ISpecGroupMapper;
import com.revanwang.ly.manage_product.mapper.ISpecParamMapper;
import com.revanwang.ly.manage_product.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
public class SpecificationServiceImpl implements ISpecificationService {

    @Autowired
    private ISpecGroupMapper specGroupMapper;
    @Autowired
    private ISpecParamMapper specParamMapper;

    @Override
    public LYRevanResponse querySpecGroupByCid(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> specGroupList = this.specGroupMapper.select(group);

        if (CollectionUtils.isEmpty(specGroupList)) {
            RevanThrowException.throwException(RevanResponseCode.CATEGORY_SPEC_GROUP_NOT_FOUND);
        }

        RevanResponseData<List<SpecGroup>> data = new RevanResponseData<>();
        data.setData(specGroupList);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, data);
    }

    @Override
    public LYRevanResponse querySpecParamByGId(Long gid) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        List<SpecParam> specParamList = this.specParamMapper.select(param);

        if (CollectionUtils.isEmpty(specParamList)) {
            RevanThrowException.throwException(RevanResponseCode.CATEGORY_SPEC_PARAM_NOT_FOUND);
        }

        RevanResponseData<List<SpecParam>> data = new RevanResponseData<>();
        data.setData(specParamList);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, data);
    }
}
