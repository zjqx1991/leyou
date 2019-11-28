package com.revanwang.ly.manage_product.mapper;

import com.revanwang.ly.domain.product.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IBrandMapper extends Mapper<Brand>, SelectByIdListMapper<Brand, Long> {

    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid}, #{bid})")
    public int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Select("SELECT tb.* FROM tb_brand tb LEFT JOIN tb_category_brand tcb ON tb.id = tcb.brand_id WHERE tcb.category_id = #{cid}")
    List<Brand> queryBrandListByCid(@Param("cid") Long cid);

}
