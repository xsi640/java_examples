package com.suyang.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	private final String GET = "SELECT * FROM Student WHERE id=?";
	private final String INSERT = "INSERT INTO Student(name, age, birthday) VALUES(?,?,?)";
	private final String UPDATE = "UPDATE Student SET name=?, age=?, birthday=? WHERE id=?";
	private final String DELETE = "DELETE FROM Student WHERE id=?";
	private final String SELECT_ALL = "SELECT * FROM Student";
	private final String DELETE_ALL = "DELETE FROM Student";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Student get(int id) {
		try {
			return jdbcTemplate.queryForObject(GET, new Object[] { id }, new RowMapper<Student>() {
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					Student s = new Student();
					s.setId(rs.getInt("id"));
					s.setName(rs.getString("name"));
					s.setAge(rs.getInt("age"));
					s.setBirthday(rs.getDate("birthday"));
					return s;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Student insert(final Student s) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, s.getName());
				statement.setInt(2, s.getAge());
				statement.setDate(3, new Date(s.getBirthday().getTime()));
				return statement;
			}
		}, holder) == 0)
			return null;
		s.setId(holder.getKey().intValue());
		return s;
	}

	public Student update(Student s) {
		if (jdbcTemplate.update(UPDATE, new Object[] { s.getName(), s.getAge(), s.getBirthday(), s.getId() }) == 0)
			return null;
		else
			return s;
	}

	public int delete(int id) {
		return jdbcTemplate.update(DELETE, new Object[] { id });
	}

	public List<Student> selectAll() {
		return jdbcTemplate.query(SELECT_ALL, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				return s;
			}
		});
	}

	public int clear() {
		return jdbcTemplate.update(DELETE_ALL);
	}

}
