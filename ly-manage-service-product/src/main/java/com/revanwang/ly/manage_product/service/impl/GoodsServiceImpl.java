package com.revanwang.ly.manage_product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.revanwang.common.exception.RevanThrowException;
import com.revanwang.common.model.LYRevanResponse;
import com.revanwang.common.model.RevanResponseCode;
import com.revanwang.common.model.RevanResponseData;
import com.revanwang.ly.domain.product.Category;
import com.revanwang.ly.domain.product.Spu;
import com.revanwang.ly.manage_product.mapper.ISpuDetailMapper;
import com.revanwang.ly.manage_product.mapper.ISpuMapper;
import com.revanwang.ly.manage_product.service.IBrandService;
import com.revanwang.ly.manage_product.service.ICategoryService;
import com.revanwang.ly.manage_product.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private ISpuMapper spuMapper;
    @Autowired
    private ISpuDetailMapper spuDetailMapper;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBrandService brandService;

    @Override
    public LYRevanResponse querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        //搜索字段过滤
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%"+ key +"%");
        }
        //上下架过滤
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        //默认排序
        example.setOrderByClause("last_update_time DESC");
        //查询
        List<Spu> spus = spuMapper.selectByExample(example);
        //判断
        if (CollectionUtils.isEmpty(spus)) {
            RevanThrowException.throwException(RevanResponseCode.GOODS_NOT_FOUND);
        }
        //解析分类和品牌的名称
        loadCategoryAndBrandName(spus);
        //解析分页结果
        PageInfo<Spu> info = new PageInfo<>(spus);

        RevanResponseData<List<Spu>> responseData = new RevanResponseData<>();
        responseData.setData(spus);
        responseData.setTotal(info.getTotal());

        return new LYRevanResponse(RevanResponseCode.SUCCESS, responseData);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {
//        for (Spu spu : spus) {
//            //处理分类名称
//            List<String> names = this.categoryService.queryByListPid(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
//                    .stream().map(Category::getName).collect(Collectors.toList());
//            spu.setCname(StringUtils.join(names, "/"));
//            //处理品牌名称
//            spu.setBname(this.brandService.queryBrandById(spu.getBrandId()).getName());
//        }
    }
}
