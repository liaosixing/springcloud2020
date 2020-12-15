package com.atguigu.springcloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/payment/")
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	@Value("${server.port}")
	private String serverPort;
	@Value("${spring.application.name}")
	private String serverName;
	@Resource
	private DiscoveryClient discoveryClient;
	@PostMapping("create")
	public CommonResult create(@RequestBody Payment payment) {
		try {
			int result = paymentService.create(payment);
			log.info("----插入结果:"+result);
			if(result > 0) {
				return new CommonResult(200, "数据插入成功 serverPort:" + serverPort, result);
			}else {
				return new CommonResult(444, "数据插入失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CommonResult(444, "数据插入失败", null);
	}
	
	@GetMapping("getPaymentById/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		System.out.println("8888");
		Payment payment = paymentService.getPaymentById(id);
		log.info("----查询结果:"+payment);
		if(payment != null) {
			return new CommonResult(200, "数据查询成功 serverPort:" + serverPort, payment);
		}else {
			return new CommonResult(444, "没有相关数据"+id, null);
		}
		
	}
	
	@GetMapping("discover")
	public Object discover() {
		List<String> list = discoveryClient.getServices();
		for (String ss : list) {
			System.out.println(ss);
		}
		
		List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
		for (ServiceInstance i : instances) {
			System.out.println(i.getServiceId()+ ";" + i.getHost() + ";" + i.getPort() );
		}
		
		return this.discoveryClient;
	}
	
	@GetMapping("paymentReadTimeOut")
	public String paymentReadTimeOut() {
		return paymentService.paymentReadTimeOut(serverPort);
	}
	
	@GetMapping("lb")
	public String paymentLB() {
		return serverPort;
	}
}
