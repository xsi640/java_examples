package com.suyang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Student create(@RequestBody Student s) {
		return studentRepository.save(s);
	}

	@RequestMapping(value = "/student", method = RequestMethod.PUT)
	public Student modify(@RequestBody Student s) {
		Student student = studentRepository.findOne(s.getId()); 
		student.setName(s.getName());
		student.setAge(s.getAge());
		student.setBirthday(s.getBirthday());
		return studentRepository.save(student);
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		studentRepository.delete(id);
	}
}
