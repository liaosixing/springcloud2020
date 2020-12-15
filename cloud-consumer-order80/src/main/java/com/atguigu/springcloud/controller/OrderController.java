package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
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
	private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
	private static final String PRE_URL = "/payment/";
	@Resource
	private RestTemplate restTemplate;
	
	
	@GetMapping("create")
	public CommonResult<Payment> create(Payment payment) {
		return restTemplate.postForObject(PAYMENT_URL + PRE_URL +"create", payment, CommonResult.class);
	}
	
	@GetMapping("getPayment/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id")Long id) {
		return restTemplate.getForObject(PAYMENT_URL + PRE_URL+ "getPaymentById/" + id, CommonResult.class);
	}
	
	@GetMapping("create/forEntity")
	public CommonResult<Payment> create2(Payment payment) {
		ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL + PRE_URL +"create", payment, CommonResult.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		}else {
			return new CommonResult<>(444, "操作失败");
		}
	}
	
	@GetMapping("getPayment/forEntity/{id}")
	public CommonResult<Payment> getPayment2(@PathVariable("id")Long id) {
		ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PAYMENT_URL + PRE_URL+ "getPaymentById/" + id, CommonResult.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity.getBody();
		}else {
			return new CommonResult<Payment>(444, "操作失败");
		}
	}
	@GetMapping("zipkin")
	public String paymentZipkin() {
		return restTemplate.getForObject(PAYMENT_URL + PRE_URL+ "paymentZipkin", String.class);
	}
}
