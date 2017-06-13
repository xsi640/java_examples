package com.suyang.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public Student get(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Student.class);
	}

	public void insert(final Student s) {
		mongoTemplate.insert(s);
	}

	public void update(Student s) {
		mongoTemplate.save(s);
	}

	public void delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Student.class);
	}

	public List<Student> selectAll() {
		return mongoTemplate.findAll(Student.class);
	}

}
