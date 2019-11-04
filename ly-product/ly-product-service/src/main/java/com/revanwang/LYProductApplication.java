package com.revanwang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.revanwang.product.mapper")
public class LYProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(LYProductApplication.class);
    }
}
