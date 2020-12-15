package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

@RestController
@RequestMapping("/payment/consumer/")
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	
	@GetMapping("getPayment/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id")Long id) {
		CommonResult result = paymentService.getPaymentById(id);
		return result;
	}
	
	@GetMapping("getPaymentTimeOUtTest")
	public String paymentReadTimeOutTest() {
		String string = paymentService.paymentReadTimeOut();
		return string;
	}
}
