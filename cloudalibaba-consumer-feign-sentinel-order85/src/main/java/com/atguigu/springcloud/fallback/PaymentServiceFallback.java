package com.atguigu.springcloud.fallback;

import org.springframework.stereotype.Component;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

@Component
public class PaymentServiceFallback implements PaymentService{

	@Override
	public CommonResult<Payment> paymentSql(Long id) {
		
		return new CommonResult(444,"服务降级返回：PaymentServiceFallback",new Payment(id, "不存在该流水号"));
	}

}
