package com.sakura.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sakura
 * @date 2023/11/15 14:14:19 周三
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
}
