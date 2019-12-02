package com.revanwang.ly.sms.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信服务配置类
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "leyou.sms")
public class LYSmsConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String verifyCodeTemplate;
}
