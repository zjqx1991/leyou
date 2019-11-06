package com.revanwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LYUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LYUploadApplication.class);
    }
}
