package com.atguigu.springcloud.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.AccountService;

@RestController
public class AccountController {
	@Resource
	private AccountService service;
	
	@RequestMapping("/account/decrease")
	public CommonResult decrease(@RequestParam("userId")Long userId,@RequestParam("money")BigDecimal money) {
		service.decrease(userId, money);
		return new CommonResult<>(200, "扣减账户余额成功");
	}
}
