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
        //1.查询当前分类id下的规格组参数
        List<SpecGroup> specGroupList = this.specGroupMapper.select(group);
        if (CollectionUtils.isEmpty(specGroupList)) {
            RevanThrowException.throwException(RevanResponseCode.CATEGORY_SPEC_GROUP_NOT_FOUND);
        }
        //2.查询当前分类id下的所有规格参数
        SpecParam specParam = new SpecParam();
        specParam.setCid(cid);
        List<SpecParam> specParams = this.specParamMapper.select(specParam);

        //3.把对应的规格组参数保存到相应的规格组的 params 中
        for (SpecGroup sgp : specGroupList) {
            for (SpecParam spm : specParams) {
                if (sgp.getId() == spm.getGroupId()) {
                    sgp.getParams().add(spm);
                }
            }
        }


        RevanResponseData<List<SpecGroup>> data = new RevanResponseData<>();
        data.setData(specGroupList);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, data);
    }

    @Override
    public LYRevanResponse querySpecParamByIds(Long cid, Long gid) {
        SpecParam param = new SpecParam();
        if (cid > 0) {
            param.setCid(cid);
        }
        if (gid > 0) {
            param.setGroupId(gid);
        }
        List<SpecParam> specParamList = this.specParamMapper.select(param);

        if (CollectionUtils.isEmpty(specParamList)) {
            RevanThrowException.throwException(RevanResponseCode.CATEGORY_SPEC_PARAM_NOT_FOUND);
        }

        RevanResponseData<List<SpecParam>> data = new RevanResponseData<>();
        data.setData(specParamList);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, data);
    }

}
