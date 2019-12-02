package com.revanwang.ly.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.revanwang.ly.usercenter.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class LYUserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(LYUserCenterApplication.class);
    }
}
