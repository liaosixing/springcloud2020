package com.atguigu.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Resource
	private PaymentDao paymentDao;
	
	@Override
	public int create(Payment payment) {
		return paymentDao.create(payment);
	}
	
	@Override
	public Payment getPaymentById(Long id) {
		return paymentDao.getPaymentById(id);
	}

	@Override
	public String paymentReadTimeOut(String serverport) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverport;
	}
}
