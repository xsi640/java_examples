package com.suyang.dao.impl;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import com.suyang.service.StudentService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoImplTest {

	@Qualifier("StudentDaoImpl")
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentService studentService;

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

	@Test
	public void test6Transactional() {
		List<Student> lists = studentDao.selectAll();
		int count = lists.size();
		try {
			studentService.insert();
		} catch (Exception e) {
		}
		List<Student> lists1 = studentDao.selectAll();
		int count1 = lists1.size();
		Assert.assertEquals(count, count1);
	}
}
