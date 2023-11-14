package com.sakura.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsulApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerConsulApplication80.class);
    }
}