package com.revanwang.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.revanwang.common.enums.LYExceptionEnum;
import com.revanwang.common.exception.LYException;
import com.revanwang.common.vo.LYPageResult;
import com.revanwang.product.domin.Category;
import com.revanwang.product.domin.Spu;
import com.revanwang.product.mapper.ISpuDetailMapper;
import com.revanwang.product.mapper.ISpuMapper;
import com.revanwang.product.service.IBrandService;
import com.revanwang.product.service.ICategoryService;
import com.revanwang.product.service.IGoodsService;
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
    public LYPageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
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
            throw new LYException(LYExceptionEnum.GOODS_NOT_FOUND);
        }
        //解析分类和品牌的名称
        loadCategoryAndBrandName(spus);
        //解析分页结果
        PageInfo<Spu> info = new PageInfo<>(spus);
        return new LYPageResult<>(info.getTotal(), spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            //处理分类名称
            List<String> names = this.categoryService.queryByListPid(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names, "/"));
            //处理品牌名称
            spu.setBname(this.brandService.queryBrandById(spu.getBrandId()).getName());
        }
    }
}
