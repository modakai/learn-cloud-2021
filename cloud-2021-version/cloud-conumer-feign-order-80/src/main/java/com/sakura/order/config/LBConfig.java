package com.sakura.order.config;

import org.springframework.web.client.RestTemplate;

/**
 * @author sakura
 * @date 2023/11/15 11:11:16 周三
 */
//@Configuration
public class LBConfig {

    //    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
