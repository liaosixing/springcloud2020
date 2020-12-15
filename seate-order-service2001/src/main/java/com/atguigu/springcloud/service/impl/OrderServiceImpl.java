package com.atguigu.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private StorageService storageService;
	
	@GlobalTransactional
	@Override
	public void create(Order order) {
		log.info("开始新建订单");
		orderDao.create(order);
		
		log.info("订单微服务开始调用库存，做扣减Count");
		storageService.decrease(order.getProductId(), order.getCount());
		log.info("订单微服务开始调用库存，做扣减end");
		
		log.info("订单微服务开始调用账户，做扣减Money");
		accountService.decrease(order.getUserId(), order.getMoney());
		log.info("订单微服务开始调用账户，做扣减end");
		
		log.info("开始修改订单状态开始");
		orderDao.update(order.getUserId(), 0);
		log.info("开始修改订单状态结束");
		
		log.info("下订单结束(*^_^*)");
		
	}

}
