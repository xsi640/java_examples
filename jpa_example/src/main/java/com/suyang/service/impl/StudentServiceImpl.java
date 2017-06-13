package com.suyang.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import com.suyang.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Transactional
	public void insert() {
		Student s = new Student();
		s.setName("error");
		s.setAge(18);
		s.setBirthday(new Date());
		studentDao.save(s);
		
		int i = 1 / 0;//模拟抛出异常
		
		Student s1 = new Student();
		s1.setName("error1");
		s1.setAge(20);
		s1.setBirthday(new Date());
		studentDao.save(s1);
	}

}
