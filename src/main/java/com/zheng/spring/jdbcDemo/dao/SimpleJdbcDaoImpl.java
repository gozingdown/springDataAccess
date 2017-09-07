package com.zheng.spring.jdbcDemo.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {

	public int getCircleCount() {
		String sql = "SELECT COUNT(*) from CIRCLE";
		return this.getJdbcTemplate().queryForInt(sql);
	}
}
