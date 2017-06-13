package com.suyang.dao;

import java.util.List;

import com.suyang.domain.Student;

public interface StudentDao {
	Student get(String id);

	void insert(Student s);

	void update(Student s);

	void delete(String id);

	List<Student> selectAll();
}
