package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.springcloud.entities.CommonResult;

@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentService {
	@GetMapping("/payment/getPaymentById/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id);
	
	@GetMapping("/payment/paymentReadTimeOut")
	public String paymentReadTimeOut();
}
