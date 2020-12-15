package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {
	private static final String PAYMENTBASE = "/payment/nacos/";
	@Resource
	private RestTemplate restTemplate;
	
	@Value("${serverApi.paymentServer}")
	private String paymentServerUrl;
	
	@GetMapping("/consumer/payment/nacos/{id}")
	public String paymentInfo(@PathVariable("id") Long id) {
		return restTemplate.getForObject(paymentServerUrl + PAYMENTBASE + id, String.class);
	}
	
	
}
