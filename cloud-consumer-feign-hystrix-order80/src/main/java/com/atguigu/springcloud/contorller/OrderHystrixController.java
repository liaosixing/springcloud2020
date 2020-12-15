package com.atguigu.springcloud.contorller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
	@Resource
	private PaymentService paymentService;
	
	@GetMapping("/consumer/hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		return paymentService.paymentInfo_OK(id);
	}

	@GetMapping("/consumer/hystrix/timeout/{id}")
//	@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallBackMethod",commandProperties = {
//		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//	})
	@HystrixCommand
	public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
		return paymentService.paymentInfo_TimeOut(id);
	}
	
	public String paymentInfo_TimeOutFallBackMethod(@PathVariable("id") Integer id) {
		return "80消费端调用8001失败   可能是8001系统超时   也可能是80消费者系统异常   请稍后再试";
	}
	
	/**
     * 全局fallback
     *
     * @return
     */
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后重试.o(╥﹏╥)o";
    }

}
