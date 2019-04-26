package com.suyang.mapper;

import com.suyang.Application;
import com.suyang.domain.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    private Student student;

    @Before
    public void setup() {
        // 清除数据
        studentMapper.deleteAll();

        // 准备测试数据
        student = new Student();
        student.setName("张三");
        student.setAge(18);
        student.setBirthday(new Date());
        studentMapper.insert(student);
    }

    @Test
    public void findOneTest() {
        Student s = studentMapper.findById(student.getId());
        Assert.assertEquals(s.getId(), student.getId());
        Assert.assertEquals(s.getName(), student.getName());
        Assert.assertEquals(s.getAge(), student.getAge());
        Assert.assertEquals(s.getBirthday().getDate(), student.getBirthday().getDate());
    }

    @Test
    public void insertCollection() {
        List<Student> students = new ArrayList<>();

        student = new Student();
        student.setName("李四");
        student.setAge(22);
        student.setBirthday(new Date());
        students.add(student);

        student = new Student();
        student.setName("王五");
        student.setAge(30);
        student.setBirthday(new Date());
        students.add(student);

        int count = studentMapper.insertCollection(students);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void insertOrUpdate() {
        student = new Student();
        student.setName("苏扬");
        student.setAge(30);
        student.setBirthday(new Date());
        studentMapper.insertOrUpdate(student);
        Assert.assertNotEquals(null, student.getId());

        student.setAge(33);
        studentMapper.insertOrUpdate(student);

        student = studentMapper.findById(student.getId());
        Assert.assertEquals(student.getAge(), new Integer(33));
    }

    @Test
    public void update() {
        student = studentMapper.findById(student.getId());
        student.setAge(10);
        student.setName("张就");
        studentMapper.update(student);

        student = studentMapper.findById(student.getId());
        Assert.assertEquals(student.getAge(), Integer.valueOf(10));
        Assert.assertEquals(student.getName(), "张就");
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setName("苏扬delete");
        student.setAge(30);
        student.setBirthday(new Date());
        studentMapper.insert(student);

        studentMapper.delete(student.getId());

        Assert.assertNull(studentMapper.findById(Integer.valueOf(student.getId())));
    }

    @Test
    public void count() {
        int count = studentMapper.count();
        Assert.assertNotEquals(0, count);
    }

    @Test
    public void deletes(){
        Student student = new Student();
        student.setName("苏扬delete1");
        student.setAge(30);
        student.setBirthday(new Date());
        studentMapper.insert(student);

        Student student1 = new Student();
        student1.setName("苏扬delete2");
        student1.setAge(30);
        student1.setBirthday(new Date());
        studentMapper.insert(student1);

        studentMapper.deletes(Arrays.asList(student.getId(), student1.getId()));


        Assert.assertNull(studentMapper.findById(Integer.valueOf(student.getId())));
        Assert.assertNull(studentMapper.findById(Integer.valueOf(student1.getId())));
    }
}
