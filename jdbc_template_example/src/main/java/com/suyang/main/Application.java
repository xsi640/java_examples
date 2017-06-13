package com.suyang.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suyang.service.StudentService;

public class Application {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentService studentService = (StudentService) ctx.getBean(StudentService.class);
		studentService.insert();		
	}
}
