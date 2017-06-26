package com.suyang.controller;

import java.util.Date;
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
	private StudentDao studentDao;

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public Student findOne(@PathVariable("id") final int id) {
		return studentDao.get(id);
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public List<Student> findAll() {
		return studentDao.selectAll();
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Student create(@RequestBody Student student) {
		return studentDao.insert(student);
	}

	@RequestMapping(value = "/student", method = RequestMethod.PUT)
	public Student modify(@RequestBody Student student) {
		return studentDao.update(student);
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		studentDao.delete(id);
	}
}
