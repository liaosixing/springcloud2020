package com.atguigu.springcloud.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FlowLimitController {
	@GetMapping("/testA")
	public String testA() {
		return "---------------testA";
	}
	@GetMapping("/testB")
	public String testB() {
		return "---------------testB";
	}
	
	@GetMapping("/testD")
	public String testD() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("测试RT");
		return "---------------testD";
	}
	
	@GetMapping("/testHotKey")
	@SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
	public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
							@RequestParam(value = "p2",required = false) String p2) {
								
		return "---------testHotKey (*^_^*)";
	}
	
	public String deal_testHotKey(@RequestParam(value = "p1",required = false) String p1,
			@RequestParam(value = "p2",required = false) String p2,BlockException ex) {
		return "------deal_testHotKey /(ㄒoㄒ)/~~";
	}
}
