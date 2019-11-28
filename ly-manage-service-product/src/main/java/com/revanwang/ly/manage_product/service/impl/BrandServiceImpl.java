package com.revanwang.ly.manage_product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.Brand;
import com.revanwang.ly.manage_product.mapper.IBrandMapper;
import com.revanwang.ly.manage_product.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static com.revanwang.common.model.RevanResponseCode.BRAND_NOT_FOUND;


@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandMapper brandMapper;

    @Override
    public LYRevanResponse queryBrandsByPage(Long page, Integer rows, String sortBy, boolean desc, String key) {
        //1、开始分页
        PageHelper.startPage(page.intValue(), rows);

        Example example = new Example(Brand.class);
        //2、过滤
        if (StringUtils.isNotBlank(key)) {
            //模糊查询名字
            example.createCriteria()
                    .orLike("name", "%"+key+"%")
                    .orEqualTo("letter", key.toUpperCase());
        }
        //3、排序
        if (StringUtils.isNotBlank(sortBy)) {
            //为 sortBy字段排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //4、查询
        List<Brand> list = this.brandMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(list)) {
            RevanThrowException.throwException(BRAND_NOT_FOUND);
        }
        //5、解析分页结果
        PageInfo<Brand> info = new PageInfo<>(list);
        RevanResponseData<List<Brand>> responseData = new RevanResponseData<>();
        responseData.setData(list);
        responseData.setTotal(info.getTotal());
        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
    }

    @Override
    @Transactional
    public LYRevanResponse saveBrand(Brand brand, List<Long> cids) {
        //1、保存品牌
        this.brandMapper.insert(brand);
        //2、保存品牌和分类中间表
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
        return new LYRevanResponse(RevanResponseCode.SUCCESS);
    }


    @Override
    public LYRevanResponse queryBrandListByCid(Long cid) {
        List<Brand> brands = this.brandMapper.queryBrandListByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            RevanThrowException.throwException(BRAND_NOT_FOUND);
        }
        RevanResponseData<List<Brand>> responseData = new RevanResponseData<>();
        responseData.setData(brands);
        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
    }

    @Override
    public LYRevanResponse queryBrandByIds(List<Long> cids) {
        if (CollectionUtils.isEmpty(cids)) {
            RevanThrowException.throwException(RevanResponseCode.PARAM_FAIL);
        }
        List<Brand> brands = this.brandMapper.selectByIdList(cids);
        if (CollectionUtils.isEmpty(cids)) {
            RevanThrowException.throwException(BRAND_NOT_FOUND);
        }
        RevanResponseData<List<Brand>> data = new RevanResponseData<>();
        data.setData(brands);

        return new LYRevanResponse(RevanResponseCode.SUCCESS, data);
    }
}
