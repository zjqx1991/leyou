package com.revanwang.ly.domain.usercenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Setter
@Getter
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 4, max = 20, message = "用户名只能在4~20位之间")
    private String username; //用户名

    @JsonIgnore
    @Length(min = 6, max = 20, message = "用户名只能在6~20位之间")
    private String password; //密码

    @Pattern(regexp = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16([5,6])|(17[0-8])|(18[0-9]))|(19[1,8,9]))\\d{8}$", message = "请输入正确的手机号")
    private String phone; //电话

    private Date created; //创建时间

    @JsonIgnore
    private String salt; //密码的盐值
}
