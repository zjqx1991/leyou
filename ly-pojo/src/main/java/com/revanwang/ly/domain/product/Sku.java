package com.revanwang.ly.domain.product;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_sku")
public class Sku {

    @Id
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String indexes;
    private String ownSpec;
    private Boolean enable;
    private Date createTime;
    private Date lastUpdateTime;
}
