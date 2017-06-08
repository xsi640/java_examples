package com.suyang.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.suyang.domain.Student;

public interface StudentDao extends Repository<Student, Integer> {
	Student findOne(int id);
	
	Student save(Student s);

	void delete(Student s);

	List<Student> findAll();
}
