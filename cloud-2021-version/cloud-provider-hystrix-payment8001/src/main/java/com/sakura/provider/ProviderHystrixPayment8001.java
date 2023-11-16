package com.sakura.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
//@EnableCircuitBreaker // 已经弃用
@EnableHystrix
public class ProviderHystrixPayment8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixPayment8001.class);
    }
}