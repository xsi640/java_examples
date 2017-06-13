package com.suyang.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import com.suyang.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Qualifier("StudentDaoImpl")
	@Autowired
	private StudentDao studentDao;

	@Transactional
	public void insert() {
		Student s = new Student();
		s.setName("error");
		s.setAge(18);
		s.setBirthday(new Date());
		studentDao.insert(s);
		int i = 1 / 0;//模拟抛出异常
		s.setName("zzz");
		studentDao.insert(s);
	}

}
