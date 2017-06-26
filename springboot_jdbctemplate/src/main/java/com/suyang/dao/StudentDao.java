package com.suyang.dao;

import java.util.List;

import com.suyang.domain.Student;

public interface StudentDao {
	Student get(int id);

	Student insert(Student s);

	Student update(Student s);

	int delete(int id);

	List<Student> selectAll();
	
	int clear();
}
