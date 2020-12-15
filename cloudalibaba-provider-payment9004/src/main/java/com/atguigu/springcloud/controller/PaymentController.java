package com.atguigu.springcloud.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	@Value("${server.port}")
	private String serverPort;
	
	public static HashMap<Long, Payment> hashMap = new HashMap<>();
	
	static {
		hashMap.put(1L, new Payment(1L, "451922ce-6188-4eb9-a923-722339fef014"));
		hashMap.put(2L, new Payment(2L, "96537a95-1f42-489f-abea-7663700dfd9a"));
		hashMap.put(3L, new Payment(3L, "419ad797-fc62-4abd-80dc-d108f2e1b0c8"));
	}
	@GetMapping("/payment/sql/{id}")
	public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
		Payment payment = hashMap.get(id);
		CommonResult<Payment> result = new CommonResult<>(200, "from mysql serverport:" + serverPort,payment);
		return result;
	}
//	public static void main(String[] args) {
//		String string = UUID.randomUUID().toString();
//		System.out.println(string);
//	}
	
}
