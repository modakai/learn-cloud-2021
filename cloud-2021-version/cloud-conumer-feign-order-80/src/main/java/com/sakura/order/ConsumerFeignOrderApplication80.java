package com.sakura.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignOrderApplication80.class);
    }
}