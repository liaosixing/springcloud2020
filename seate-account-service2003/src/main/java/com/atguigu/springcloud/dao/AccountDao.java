package com.atguigu.springcloud.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
	public void decrease(@Param("userId") Long userId,@Param("money")BigDecimal money);
}
