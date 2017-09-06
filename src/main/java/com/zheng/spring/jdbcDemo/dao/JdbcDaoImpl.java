package com.zheng.spring.jdbcDemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.zheng.spring.jdbcDemo.model.Circle;

@Component
public class JdbcDaoImpl {

	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

//	public Circle getCircle(int circleId) {
//		Connection conn = null;
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
//			ps.setInt(1, circleId);
//			Circle circle = null;
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				circle = new Circle(circleId, rs.getString("name"));
//			}
//			rs.close();
//			ps.close();
//			return circle;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//			}
//		}
//	}

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

	public Circle getCircleForId(int circleId) {
		String sql = "select * from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {circleId}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles() {
		String sql = "select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	private static final class CircleMapper implements RowMapper<Circle> {

		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(resultSet.getInt("id"));
			circle.setName(resultSet.getString("name"));
			return circle;
		}
		
	}
	
	public void insertCircle(Circle circle) {
		String sql = "insert into circle (id, name) values (?,?)";
		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
	}
	
	// just for example, normally it should be part of a initialization script
	public void createTriangleTable() {
		String sql = "create table triangle (id integer, name varchar(50))";
		jdbcTemplate.execute(sql);
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
