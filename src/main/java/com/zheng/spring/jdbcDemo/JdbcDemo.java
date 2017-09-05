package com.zheng.spring.jdbcDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zheng.spring.jdbcDemo.dao.JdbcDaoImpl;
import com.zheng.spring.jdbcDemo.model.Circle;

public class JdbcDemo {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		
		Circle circle = dao.getCircle(1);
		System.out.println(circle.getName());
	}
}