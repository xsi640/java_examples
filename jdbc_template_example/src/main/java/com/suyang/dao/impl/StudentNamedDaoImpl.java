package com.suyang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;


@Repository(value="StudentNamedDaoImpl")
public class StudentNamedDaoImpl implements StudentDao {

	private final String GET = "SELECT * FROM Student WHERE id=:id";
	private final String INSERT = "INSERT INTO Student(name, age, birthday) VALUES(:name,:age,:birthday)";
	private final String UPDATE = "UPDATE Student SET name=:name, age=:age, birthday=:birthday WHERE id=:id";
	private final String DELETE = "DELETE FROM Student WHERE id=:id";
	private final String SELECT_ALL = "SELECT * FROM Student";

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public Student get(int id) {
		return namedJdbcTemplate.queryForObject(GET, Collections.singletonMap("id", id), this.getRowMap());
	}

	public Student insert(Student s) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		if(namedJdbcTemplate.update(INSERT, 
				new MapSqlParameterSource("name", s.getName())
				.addValue("age", s.getAge())
				.addValue("birthday", s.getBirthday()), holder) == 0)
			return null;
		s.setId(holder.getKey().intValue());
		return s;
	}

	public Student update(Student s) {
		if(namedJdbcTemplate.update(UPDATE, 
				new MapSqlParameterSource("name", s.getName())
				.addValue("age", s.getAge())
				.addValue("birthday", s.getBirthday())
				.addValue("id", s.getId())) == 0)
			return null;
		else
			return s;
	}

	public int delete(int id) {
		return namedJdbcTemplate.update(DELETE, Collections.singletonMap("id", id));
	}

	public List<Student> selectAll() {
		return namedJdbcTemplate.query(SELECT_ALL, getRowMap());
	}

	public RowMapper<Student> getRowMap(){
		return new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));
				return s;
			}
		};
	}
}
