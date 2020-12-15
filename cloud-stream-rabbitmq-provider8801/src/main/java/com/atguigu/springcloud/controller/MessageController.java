package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.IMessageProvider;

@RestController
public class MessageController {
	@Resource
	private IMessageProvider provider;
	
	@GetMapping("/sendMessage")
	public String sendMessage() {
		return provider.send();
	}
}
