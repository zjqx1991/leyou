package com.revanwang.product.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.revanwang.common.exception.LYException;
import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Brand;
import com.revanwang.product.mapper.IBrandMapper;
import com.revanwang.product.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static com.revanwang.common.enums.LYExceptionEnum.BRAND_NOT_FOUND;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandMapper brandMapper;

    @Override
    public LYPageResult<Brand> queryBrandsByPage(Long page, Integer rows, String sortBy, boolean desc, String key) {
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
            throw new LYException(BRAND_NOT_FOUND);
        }
        //5、解析分页结果
        PageInfo<Brand> info = new PageInfo<>(list);
        return new LYPageResult<Brand>(info.getTotal(), list);
    }
}
