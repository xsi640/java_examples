package com.suyang.domain;

import java.util.Date;

public class Student {
    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.Integer age;
    private java.util.Date birthday;

    public Student() { }

    public Student(java.lang.String name, java.lang.Integer age, java.util.Date birthday) {
            this.name = name;
            this.age = age;
            this.birthday = birthday;
    }

    public java.lang.Integer getId(){
        return this.id;
    }

    public void setId(java.lang.Integer id){
        this.id = id;
    }

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.Integer getAge() {
        return this.age;
    }

    public void setAge(java.lang.Integer age) {
        this.age = age;
    }

    public java.util.Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + this.id +
               "name=" + this.name +
               "age=" + this.age +
               "birthday=" + this.birthday +
               '}';
    }
}