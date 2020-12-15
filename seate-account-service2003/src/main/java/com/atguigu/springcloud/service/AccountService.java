package com.atguigu.springcloud.service;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestParam;


public interface AccountService {
	public void decrease(@RequestParam("userId")Long userId,@RequestParam("money")BigDecimal money);
}
