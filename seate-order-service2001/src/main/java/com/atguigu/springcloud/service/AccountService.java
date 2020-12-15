package com.atguigu.springcloud.service;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springcloud.domain.CommonResult;

@FeignClient(value = "seata-account-service")
public interface AccountService {

	@PostMapping("/account/decrease")
	public CommonResult decrease(@RequestParam("userId") Long userId,@RequestParam("money")BigDecimal money);
}
