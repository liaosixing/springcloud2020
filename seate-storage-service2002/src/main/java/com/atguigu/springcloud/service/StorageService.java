package com.atguigu.springcloud.service;


import com.atguigu.springcloud.domain.CommonResult;

public interface StorageService {
	
	public CommonResult decrease(Long productId,Integer count);
}
