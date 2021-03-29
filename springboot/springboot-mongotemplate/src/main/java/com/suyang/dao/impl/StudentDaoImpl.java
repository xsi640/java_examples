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

	@Override
	public Student findOne(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Student.class);
	}

	@Override
	public List<Student> findAll() {
		return mongoTemplate.findAll(Student.class);
	}

	@Override
	public Student save(Student s) {
		mongoTemplate.save(s);
		return s;
	}

	@Override
	public void delete(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, Student.class);
	}

	@Override
	public void deleteAll() {
		mongoTemplate.dropCollection(Student.class);
	}

}
