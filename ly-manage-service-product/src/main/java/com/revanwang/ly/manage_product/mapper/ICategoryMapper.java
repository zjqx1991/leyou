package com.revanwang.ly.manage_product.mapper;


import com.revanwang.ly.domain.product.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ICategoryMapper extends Mapper<Category>, IdListMapper<Category, Long> {
}
