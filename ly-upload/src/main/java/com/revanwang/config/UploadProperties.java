package com.revanwang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 加载Application.yml中符合 ly.upload开始的配置信息
 */
@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {
    private String baseURL;
    private List<String> allowTypes;
}
