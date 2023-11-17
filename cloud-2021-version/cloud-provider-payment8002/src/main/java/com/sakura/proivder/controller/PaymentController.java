package com.sakura.proivder.controller;


import com.sakura.cloud.common.entities.CommonResult;
import com.sakura.cloud.common.entities.Payment;
import com.sakura.proivder.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/lb")
    public String lb() {
        return serverPort;
    }

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功 port：" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败 port：" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功 port：" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录,查询ID: " + id + "port" + serverPort, null);
        }
    }
}