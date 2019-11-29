package com.revanwang.ly.domain.product;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 规格组
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;            //主键
    private Long cid;           //商品分类
    private String name;        //规格组名称

    @Transient
    private List<SpecParam> params = new ArrayList<>(); //该组下的所有规格参数集合
}
