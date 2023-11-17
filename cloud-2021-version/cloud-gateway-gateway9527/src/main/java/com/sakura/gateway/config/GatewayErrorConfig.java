package com.sakura.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GatewayErrorConfig {

    @Bean
    @Order(-1)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectMapper objectMapper) {
        return new GlobalErrorWebExceptionHandler(objectMapper);
    }

    private static class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

        private final ObjectMapper objectMapper;

        public GlobalErrorWebExceptionHandler(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @SneakyThrows
        @Override
        public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
            if (ex instanceof ResponseStatusException) {
                ResponseStatusException responseStatusException = (ResponseStatusException) ex;
                HttpStatus status = responseStatusException.getStatus();
                String errorMessage;
                if (status == HttpStatus.NOT_FOUND) {
                    errorMessage = "请求的路径不存在，请检查路径是否正确。";
                } else if (status == HttpStatus.UNAUTHORIZED) {
                    errorMessage = "请求未经授权，请提供有效的身份信息。";
                } else {
                    errorMessage = "请求处理失败，状态码：" + status.value();
                }
                exchange.getResponse().setStatusCode(status);
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", errorMessage);
                String jsonError = objectMapper.writeValueAsString(errorResponse);

                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory().wrap(jsonError.getBytes())));
            }

            // 其他异常处理逻辑
            // ...

            // 默认情况，返回通用错误信息
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> defaultErrorResponse = new HashMap<>();
            defaultErrorResponse.put("error", "请求处理失败，请检查请求参数或路径是否正确。");
            String jsonDefaultError = objectMapper.writeValueAsString(defaultErrorResponse);

            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                    .bufferFactory().wrap(jsonDefaultError.getBytes())));
        }
    }
}
