package com.revanwang.ly.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 短信微服务
 */

@EnableDiscoveryClient
@SpringBootApplication
public class LYSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LYSmsApplication.class);
    }
}
