package com.sakura.proivder.mapper;

import com.sakura.proivder.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sakura
 * @date 2023/11/11 17:17:19 周六
 */
@Mapper
public interface PaymentMapper {


    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
