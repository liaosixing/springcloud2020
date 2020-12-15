package com.atguigu.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.StorageService;

@RestController
public class StorageController {
	@Resource
	private StorageService service;
	
	@RequestMapping("/storage/decrease")
	public CommonResult decrease(Long productId,Integer count) {
		service.decrease(productId, count);
		return new CommonResult<>(200, "扣减库存成功");
	}
}
