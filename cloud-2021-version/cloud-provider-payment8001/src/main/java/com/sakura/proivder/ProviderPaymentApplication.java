package com.sakura.proivder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 启动 eureka client
@EnableEurekaClient
public class ProviderPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApplication.class);
    }
}