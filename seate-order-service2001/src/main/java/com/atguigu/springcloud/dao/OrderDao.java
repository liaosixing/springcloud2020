package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.atguigu.springcloud.domain.Order;

@Mapper
public interface OrderDao {
	public void create(Order order);
	
	public void update(@Param("userId") Long userId,@Param("status") Integer status); 
}
