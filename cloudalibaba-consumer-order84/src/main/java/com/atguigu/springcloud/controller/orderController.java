package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class orderController {
	private static final String PAYMENTBASE = "/payment/sql/";
	@Resource
	private RestTemplate restTemplate;
	
	@Value("${serverApi.paymentServer}")
	private String paymentServerUrl;
	
	@GetMapping("/consumer/fallback/{id}")
//	@SentinelResource(value = "fallback",fallback = "handleFallback")
//	@SentinelResource(value = "fallback",blockHandler = "blockHandler")
	@SentinelResource(value = "fallback",
		blockHandler = "blockHandler",
		fallback = "handleFallback",
		exceptionsToIgnore = {IllegalArgumentException.class})
	public CommonResult<Payment> fallback(@PathVariable("id") long id){
		CommonResult result = restTemplate.getForObject(paymentServerUrl + PAYMENTBASE + id, CommonResult.class);
		if(id == 4) {
			throw new IllegalArgumentException("IllegalArgumentException-----非法参数异常");
		}else if (result.getData() == null) {
			throw new NullPointerException("NullPointerException----该id没有对应记录，空指针异常");
		}
		return result;
	}
	
	public CommonResult<Payment> handleFallback(@PathVariable("id") long id,Throwable e){
		Payment payment = new Payment(id, null);
		return new CommonResult(444,"熔断异常handleFallbackException，exception内容：" + e.getMessage(),payment);
	}
	
	public CommonResult<Payment> blockHandler(@PathVariable("id") long id,BlockException e){
		Payment payment = new Payment(id, null);
		return new CommonResult(445,"BlockException-sentinel限流，BlockException：" + e.getMessage(),payment);
	}
}
