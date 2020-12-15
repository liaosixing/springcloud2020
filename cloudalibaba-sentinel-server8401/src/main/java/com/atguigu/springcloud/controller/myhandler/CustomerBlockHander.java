package com.atguigu.springcloud.controller.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public class CustomerBlockHander {
	
	public static CommonResult handleException1(BlockException exception) {
		return new CommonResult(200,"客户自定义 global handle Exception------1");
	}
	
	public static CommonResult handleException2(BlockException exception) {
		return new CommonResult(200,"客户自定义 global handle Exception------2");
	}
}
