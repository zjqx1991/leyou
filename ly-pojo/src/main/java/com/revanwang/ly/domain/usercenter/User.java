package com.revanwang.ly.domain.usercenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; //用户名

    @JsonIgnore
    private String password; //密码

    private String phone; //电话

    private Date created; //创建时间

    @JsonIgnore
    private String salt; //密码的盐值
}
