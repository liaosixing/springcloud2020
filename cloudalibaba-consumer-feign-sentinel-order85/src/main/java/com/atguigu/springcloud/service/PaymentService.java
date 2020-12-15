package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.fallback.PaymentServiceFallback;

@Component
@FeignClient(value = "nacos-sentinel-payemnt",fallback = PaymentServiceFallback.class)
public interface PaymentService {

	@GetMapping("/payment/sql/{id}")
	public CommonResult<Payment> paymentSql(@PathVariable("id") Long id);
}
