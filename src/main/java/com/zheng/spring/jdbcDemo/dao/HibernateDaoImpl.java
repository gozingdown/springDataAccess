package com.zheng.spring.jdbcDemo.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zheng.spring.jdbcDemo.model.Circle;

@Repository
public class HibernateDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int getCircleCount() {
		String hql = "select count(*) from " + Circle.class.getName();
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}
}
