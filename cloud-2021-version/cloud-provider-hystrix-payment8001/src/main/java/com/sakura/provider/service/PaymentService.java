package com.sakura.provider.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问，一切OK
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            // 自身调用超时时间的峰值
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O，耗费5秒";
    }

    /**
     * Hystrix fallback
     *
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "服务繁忙！请稍后再测试！";
    }
}