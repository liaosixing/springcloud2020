package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springcloud.domain.CommonResult;

@FeignClient(value = "seata-storage-service")
public interface StorageService {
	
	@PostMapping("/storage/decrease")
	public CommonResult decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}
