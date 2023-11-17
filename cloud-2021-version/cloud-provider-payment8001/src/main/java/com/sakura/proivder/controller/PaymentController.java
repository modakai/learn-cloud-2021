package com.sakura.proivder.controller;


import com.sakura.cloud.common.entities.CommonResult;
import com.sakura.cloud.common.entities.Payment;
import com.sakura.proivder.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/lb")
    public String lb() {
        return serverPort;
    }

    @GetMapping("/payment/discover")
    public CommonResult<DiscoveryClient> getDiscovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            // 服务名称
            log.info("*****service**** {}", service);
        }

        // 根据 eureka 的 application 获取对应的微服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            // 服务id
            String serviceId = instance.getServiceId();
            // 协议
            String scheme = instance.getScheme();
            // 服务端口号
            int port = instance.getPort();
            // 服务前缀
            URI uri = instance.getUri();
            // 实例的id
            String instanceId = instance.getInstanceId();
            log.info(serviceId + "\t" + scheme + "\t" + port + "\t" + uri + "\t" + instanceId);
        }

        return new CommonResult<>(200, "成功", discoveryClient);
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

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut() {
        System.out.println("*****paymentFeignTimeOut from port: " + serverPort);
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}