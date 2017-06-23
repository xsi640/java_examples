package com.suyang.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;

@RestController
public class StudentController {

	@Autowired
	private StudentDao studentRepository;

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public Student findOne(@PathVariable("id") final String id) {
		return studentRepository.findOne(id);
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Student create(String id, String name, Integer age, Date birthday) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setAge(age);
		student.setBirthday(birthday);
		return studentRepository.save(student);
	}

	@RequestMapping(value = "/student", method = RequestMethod.PUT)
	public Student modify(String id, String name, Integer age, Date birthday) {
		Student student = studentRepository.findOne(id); 
		student.setName(name);
		student.setAge(age);
		student.setBirthday(birthday);
		return studentRepository.save(student);
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		studentRepository.delete(id);
	}
}
