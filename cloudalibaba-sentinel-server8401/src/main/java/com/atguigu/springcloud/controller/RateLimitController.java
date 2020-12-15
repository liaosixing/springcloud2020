package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.controller.myhandler.CustomerBlockHander;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RateLimitController {

	@GetMapping("/byResource")
	@SentinelResource(value = "byResource",blockHandler = "handlerExcetion")
	public CommonResult byResource() {
		return new CommonResult(200,"资源名称限流测试OK", new Payment(2020L, "serial1001"));
	}
	
	@GetMapping("/byUrl")
	@SentinelResource(value = "byUrl")
	public CommonResult<Payment> byUrl() {
		return new CommonResult<Payment>(200,"路径限流测试OK", new Payment(2020L, "serial1002"));
	}
	
	public CommonResult handlerExcetion(BlockException ex) {
		return new CommonResult(444,ex.getClass().getCanonicalName()+"\t 服务不可用");
	}
	//========================================================
	
	@GetMapping("/rateLimit/customerBlockHander")
	@SentinelResource(value = "customerBlockHander",
			blockHandlerClass = CustomerBlockHander.class,
			blockHandler = "handleException1")
	public CommonResult customerBlockHander() {
		return new CommonResult(200,"用户自定义", new Payment(2020L, "serial1003"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
