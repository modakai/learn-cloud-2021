package com.sakura.order.service.fallback;

import com.sakura.order.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @date 2023/11/16 21:21:07 周四
 */
@Component
public class PaymentHystrixFallBackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
