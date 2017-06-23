package com.suyang.dao;

import java.util.List;

import com.suyang.domain.Student;

public interface StudentDao{
	Student findOne(String id);
	List<Student> findAll();
	Student save(Student s);
	void delete(String id);
}
