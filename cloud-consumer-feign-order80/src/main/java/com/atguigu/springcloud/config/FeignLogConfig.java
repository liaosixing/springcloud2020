package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignLogConfig {
	@Bean
	public Logger.Level feignLoggerLevel() {
		// 请求和响应的头信息,请求和响应的正文及元数据
		return Logger.Level.FULL;
	}
}
