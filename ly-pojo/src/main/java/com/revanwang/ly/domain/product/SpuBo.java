package com.revanwang.ly.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.List;

@Setter
@Getter
public class SpuBo extends Spu {

    @Transient
    String cname;// 商品分类名称
    @Transient
    String bname;// 品牌名称
    @Transient
    SpuDetail spuDetail;// 商品详情
    @Transient
    List<Sku> skus;// sku列表
}