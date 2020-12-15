package com.atguigu.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.StorageDao;
import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.StorageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
	
	@Resource
	private StorageDao dao;
	
	@Override
	public CommonResult decrease(Long productId, Integer count) {
		log.info("StorageService 扣减库存开始");
		dao.decrease(productId, count);
		log.info("StorageService 扣减库存结束");
		return null;
	}

}
