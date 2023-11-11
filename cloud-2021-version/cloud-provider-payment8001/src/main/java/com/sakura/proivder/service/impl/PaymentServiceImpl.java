package com.sakura.proivder.service.impl;

import com.sakura.proivder.entities.Payment;
import com.sakura.proivder.mapper.PaymentMapper;
import com.sakura.proivder.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
