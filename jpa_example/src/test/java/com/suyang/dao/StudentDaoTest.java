package com.suyang.dao;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")  
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest{

	@Autowired
	private StudentDao studentDao;

	@Test
	public void test1Insert() {
		Student s = new Student();
		s.setName("张三");
		s.setAge(18);
		s.setBirthday(new Date());
		Assert.assertNotNull(studentDao.save(s));
		Assert.assertTrue(s.getId() != 0);
	}

	@Test
	public void test2SelectAll() {
		List<Student> lists = studentDao.findAll();
		Assert.assertTrue(lists.size() > 0);
	}

	@Test
	public void test3Update() {
		List<Student> lists = studentDao.findAll();
		Student student = lists.get(0);
		student.setName("李四");
		Assert.assertNotNull(studentDao.save(student));
	}

	@Test
	public void test4Get() {
		List<Student> lists = studentDao.findAll();
		Student student = lists.get(0);
		Assert.assertNotNull(studentDao.findOne(student.getId()));
	}

	@Test
	public void test5Delete() {
		List<Student> lists = studentDao.findAll();
		Student student = lists.get(0);
		studentDao.delete(student);
	}
}