package com.revanwang.ly.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_spu")
public class Spu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;                //supid
    private Long brandId;           //商品所属品牌id
    private Long cid1;              //1级类目id
    private Long cid2;              //2级类目id
    private Long cid3;              //3级类目id
    private String title;           //标题
    private String subTitle;        //子标题
    private Boolean saleable;       //是否上架，0下架、1上架
    private Boolean valid;          //是否有效，0已删除，1有效
    private Date createTime;        //创建时间
    @JsonIgnore //返回数据时，忽略此字段
    private Date lastUpdateTime;    //最后修改时间

    @Transient  //数据库中没有该字段
    private String bname;           //商品名称
    @Transient
    private String cname;
}
