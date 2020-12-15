package com.atguigu.springcloud.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.hutool.core.util.IdUtil;

@Service
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentServiceImpl implements PaymentService{

	  /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_OK,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 超时访问
     *
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",commandProperties = {
//    		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    @HystrixCommand(commandProperties = {
    		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 7;
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" +
                "O(∩_∩)O哈哈~  耗时(秒)" + timeNumber;
    }
    
    public String payment_TimeOutHandler(Integer id) {
    	 return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" +
                 "o(╥﹏╥)o 耗时(秒)" ;
    }

    /**
     * 全局fallback
     *
     * @return
     */
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后重试.o(╥﹏╥)o";
    }

	
    
    //=====================服务熔断机制===============================
    
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
    	@HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启熔断
    	@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
    	@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
    	@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少熔断
    })
	public String paymentCircuitBreaker(Integer id) {
		if(id < 0) {
			throw new RuntimeException("-------id 不能为负数");
		}
		String serialNumber = IdUtil.simpleUUID();
		
		return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
	}
    
    public String paymentCircuitBreaker_fallback(Integer id) {
    	return "id不能为负数   请稍后再试   o(╥﹏╥)o ~~~ id:"+id;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
