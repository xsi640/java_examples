package com.suyang.mapper;

import com.suyang.domain.Student;

import java.util.List;

public interface StudentMapper {
    Student findOneById(Integer id);

    int insert(Student record);

    int update(Student record);

    int deleteAll();

    int deleteById(Integer id);

    List<Student> findAll();
}