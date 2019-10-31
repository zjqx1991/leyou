package com.revanwang.product;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LYProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(LYProductApplication.class);
    }
}
