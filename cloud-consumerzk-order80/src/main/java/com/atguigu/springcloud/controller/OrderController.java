package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@RestController
@Slf4j
@RequestMapping("/consumer/payment/")
public class OrderController {
//	private static final String PAYMENT_URL = "http://localhost:8001/payment/";
//	private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
	private static final String INVOKE_URL = "http://cloud-provider-payment";
	private static final String PRE_URL = "/payment/";
	@Resource
	private RestTemplate restTemplate;
	
	
	@GetMapping("create")
	public CommonResult<Payment> create(Payment payment) {
		return restTemplate.postForObject(INVOKE_URL + PRE_URL +"create", payment, CommonResult.class);
	}
	
	@GetMapping("getPayment/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id")Long id) {
		return restTemplate.getForObject(INVOKE_URL + PRE_URL+ "getPaymentById/" + id, CommonResult.class);
	}
	
    @GetMapping("zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zktest", String.class);
    }

}
