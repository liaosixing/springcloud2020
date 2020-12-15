package com.atguigu.springcloud.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.AccountDao;
import com.atguigu.springcloud.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Resource
	private AccountDao dao;
	@Override
	public void decrease(Long userId, BigDecimal money) {
		log.info("AccountService 扣减账户余额开始");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.decrease(userId, money);
		log.info("AccountService 扣减账户余额结束");

	}

}
