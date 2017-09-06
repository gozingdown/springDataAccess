package com.zheng.spring.jdbcDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zheng.spring.jdbcDemo.model.Circle;

@Component
public class JdbcDaoImpl {

	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public Circle getCircle(int circleId) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, circleId);
			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));
			}
			rs.close();
			ps.close();
			return circle;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public int getCircleCount() {
		String sql = "SELECT COUNT(*) from CIRCLE";
		return jdbcTemplate.queryForInt(sql);
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public String getCircleName(int circleId) {
		String sql = "select name from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId},String.class);
	}

//	public Circle getCircleForId(int circleId) {
//		String sql = "select * from circle where id = ?";
//		jdbcTemplate.queryForObject(sql, new Object[] {circleId}, Circle.class);
//	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
