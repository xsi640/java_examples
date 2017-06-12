package com.suyang.dao.impl;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;

import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoImplTest {

	private StudentDao studentDao = new StudentDaoImpl();

	@Test
	public void test1Insert() {
		Student s = new Student();
		s.setName("张三");
		s.setAge(18);
		s.setBirthday(new Date());
		Assert.assertNotNull(studentDao.insert(s));
		Assert.assertTrue(s.getId() != 0);
	}

	@Test
	public void test2SelectAll() {
		List<Student> lists = studentDao.selectAll();
		Assert.assertTrue(lists.size() > 0);
	}

	@Test
	public void test3Update() {
		List<Student> lists = studentDao.selectAll();
		Student student = lists.get(0);
		student.setName("李四");
		Assert.assertNotNull(studentDao.update(student));
	}

	@Test
	public void test4Get() {
		List<Student> lists = studentDao.selectAll();
		Student student = lists.get(0);
		Assert.assertNotNull(studentDao.get(student.getId()));
	}

	@Test
	public void test5Delete() {
		List<Student> lists = studentDao.selectAll();
		Student student = lists.get(0);
		Assert.assertTrue(studentDao.delete(student.getId()) > 0);
	}
}