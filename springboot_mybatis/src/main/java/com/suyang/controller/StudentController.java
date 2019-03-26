package com.suyang.controller;

import com.suyang.domain.Student;
import com.suyang.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student findOne(@PathVariable("id") final int id) {
        return studentMapper.findOneById(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public Student create(String name, int age, Date birthday) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setBirthday(birthday);
        studentMapper.insert(student);
        return student;
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public Student modify(int id, String name, int age, Date birthday) {
        Student student = studentMapper.findOneById(id);
        student.setName(name);
        student.setAge(age);
        student.setBirthday(birthday);
        studentMapper.update(student);
        return student;
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        studentMapper.deleteById(id);
    }
}
