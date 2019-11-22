package com.revanwang.ly.domain.product;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规格组中具体参数
 */
@Data
@Table(name = "tb_spec_param")
public class SpecParam {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;                //主键
    private Long cid;               //商品分类id
    private Long groupId;           //规格组id
    private String name;            //参数名称
    @Column(name = "`numeric`")
    private boolean numeric;        //是否是数组类型参数
    private String unit;            //numeric为数字类型的单位，非数字类型可为空
    private boolean generic;        //是否是sku通用属性，true或false
    private boolean searching;      //是否用于搜索过滤，true或false
    private String segments;        //数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0

}
