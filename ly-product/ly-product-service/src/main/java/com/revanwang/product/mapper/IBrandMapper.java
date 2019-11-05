package com.revanwang.product.mapper;

import com.revanwang.product.domin.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface IBrandMapper extends Mapper<Brand> {

    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid}, #{bid})")
    public int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

}
