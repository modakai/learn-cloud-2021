package com.sakura.proivder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 启动 eureka client
@EnableEurekaClient
@EnableDiscoveryClient
public class ProviderPaymentApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApplication8001.class);
    }
}