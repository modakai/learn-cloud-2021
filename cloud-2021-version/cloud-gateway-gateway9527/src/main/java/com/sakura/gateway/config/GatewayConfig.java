package com.sakura.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sakura
 * @date 2023/11/17 22:22:13 周五
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("baidu1", r -> r.path("/guonei").uri("https://news.baidu.com/guonei"))
                .route("baidu2", r -> r.path("/mil").uri("https://news.baidu.com/mil")).build();
    }
}
