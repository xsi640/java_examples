package com.suyang.mapper;

import com.suyang.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
    @Insert("insert into tb_student (id, name, age, birthday) values(#{id}, #{name}, #{age}, #{birthday})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Student student);
    @Insert({
            "<script>",
            "insert into tb_student(id, name, age, birthday) values",
            "<foreach collection='lists' item='item' index='index' separator=','>",
            "(#{item.id}, #{item.name}, #{item.age}, #{item.birthday})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertCollection(@Param("lists") List<Student> students);
    @Insert("insert into tb_student (id, name, age, birthday) values(#{id}, #{name}, #{age}, #{birthday}) on duplicate key update name=#{name}, age=#{age}, birthday=#{birthday}")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertOrUpdate(Student student);
    @Update("update tb_student set name=#{name}, age=#{age}, birthday=#{birthday} where id=#{id}")
    int update(Student student);
    @Delete("delete from tb_student where id=#{id}")
    int delete(java.lang.Integer id);
    @Delete("delete from tb_student")
    int deleteAll();
    @Delete({
            "<script>",
            "delete from tb_student where id in",
            "<foreach collection='list' item='item' open='(' separator=',' close=')' >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    int deletes(@Param("list") List<java.lang.Integer> ids);
    List<Student> findAll();
    @Select("select * from tb_student where id=#{id}")
    Student findById(java.lang.Integer id);
    @Select("select count(*) from tb_student")
    int count();
}