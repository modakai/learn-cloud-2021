package com.sakura.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sakura
 * @date 2023/11/12 15:15:27 周日
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer2Application.class);
    }
}
