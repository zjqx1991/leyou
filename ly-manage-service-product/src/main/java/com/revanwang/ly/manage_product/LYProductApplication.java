package com.revanwang.ly.manage_product;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.revanwang.ly.manage_product.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class LYProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(LYProductApplication.class);
    }
}
