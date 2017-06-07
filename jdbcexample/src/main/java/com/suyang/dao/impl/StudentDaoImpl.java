package com.suyang.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.suyang.dao.StudentDao;
import com.suyang.dao.utils.DBUtils;
import com.suyang.domain.Student;

public class StudentDaoImpl implements StudentDao {

	private final String GET = "SELECT * FROM Student WHERE id=?";
	private final String INSERT = "INSERT INTO Student(name, age, birthday) VALUES(?,?,?)";
	private final String UPDATE = "UPDATE Student SET name=?, age=?, birthday=? WHERE id=?";
	private final String DELETE = "DELETE FROM Student WHERE id=?";
	private final String SELECT_ALL = "SELECT * FROM Student";

	public Student get(int id) {
		Student result = null;
		Connection conn = DBUtils.getConnection();
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement(GET);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result = new Student();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setAge(rs.getInt("age"));
				result.setBirthday(rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public Student insert(Student s) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, s.getName());
			statement.setInt(2, s.getAge());
			statement.setDate(3, new Date(s.getBirthday().getTime()));
			if (statement.executeUpdate() == 0)
				return null;
			ResultSet tableKeys = statement.getGeneratedKeys();
			tableKeys.next();
			s.setId(tableKeys.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return s;
	}

	public Student update(Student s) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(UPDATE);
			statement.setString(1, s.getName());
			statement.setInt(2, s.getAge());
			statement.setDate(3, new Date(s.getBirthday().getTime()));
			statement.setInt(4, s.getId());
			if (statement.executeUpdate() == 0)
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return s;
	}

	public int delete(int id) {
		int result = 0;
		Connection conn = DBUtils.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(DELETE);
			statement.setInt(1, id);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public List<Student> selectAll() {
		List<Student> result = new ArrayList<Student>();
		Connection conn = DBUtils.getConnection();
		Statement statement = null;

		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setBirthday(rs.getDate("birthday"));

				result.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

}
