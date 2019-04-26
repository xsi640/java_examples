package com.suyang.mapper;

import com.suyang.domain.Student;

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface StudentMapper {

    int insert(Student student);

    int insertCollection(List<Student> students);

    int insertOrUpdate(Student student);

    int insertOrUpdateCollection(List<Student> students);

    int update(Student student);

    int delete(java.lang.Integer id);

    int deleteAll();

    int deletes(List<java.lang.Integer> ids);

    List<Student> findAll();

    List<Student> findByWhere(@Param("where") String where);

    List<Student> findByWhereOrder(@Param("where") String where, @Param("order") String order);

    List<Student> findByLimit(@Param("offset") String offset, @Param("limit") String limit);

    List<Student> findByWhereLimit(@Param("where") String where, @Param("offset") String offset, @Param("limit") String limit);

    List<Student> findByWhereOrderLimit(@Param("where") String where, @Param("order") String order, @Param("offset") String offset, @Param("limit") String limit);

    Student findById(java.lang.Integer id);

    int count();

    int count(@Param("where") String where);
}