package com.zheng.spring.jdbcDemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Circle {
	@Id
	private int id;
	private String name;
	
	public Circle(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Circle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
