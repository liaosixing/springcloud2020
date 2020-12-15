package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator locator(RouteLocatorBuilder builder) {
		Builder routes = builder.routes();
		routes.route("test1", r -> r.path("/guonei").uri("http://news.baidu.com"));
		return routes.build();
	}
	
	@Bean
	public RouteLocator locator2(RouteLocatorBuilder builder) {
		Builder routes = builder.routes();
		routes.route("test2", r -> r.path("/payment/paymentReadTimeOut").uri("lb://cloud-payment-service"));
		return routes.build();
	}
}
