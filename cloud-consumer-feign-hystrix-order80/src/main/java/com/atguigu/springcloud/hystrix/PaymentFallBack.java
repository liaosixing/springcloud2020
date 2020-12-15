package com.atguigu.springcloud.hystrix;

import org.springframework.stereotype.Component;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;

@Component
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentFallBack implements PaymentService{

	@Override
	public String paymentInfo_OK(Integer id) {

		return "80端调用8001的paymentInfo_OK方法失败";
	}

	@Override
	public String paymentInfo_TimeOut(Integer id) {

		return "80端调用8001的paymentInfo_TimeOut方法失败";
	}
	
	public String payment_Global_FallbackMethod() {
        return "Feign调用失败的全局熔断处理";
    }

}
