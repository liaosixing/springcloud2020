package com.atguigu.springcloud.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Long id;
	
	private Long userId;
	
	private Long productId;
	
	private Integer count;
	
	private BigDecimal money;
	// 0创建中   1已完结
	private Integer status; 
}
