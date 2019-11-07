package com.revanwang.product.domin;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long supId;             //对应的spu的id
    private String description;     //商品描述信息
    private String specialSpec;     //商品特殊规格的名称及可选值模板
    private String genericSpec;     //商品的通用规格属性
    private String packingList;     //包装清单
    private String afterService;    //售后服务
}
