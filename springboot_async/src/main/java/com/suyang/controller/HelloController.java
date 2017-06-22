package com.suyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suyang.Task;

@Controller
public class HelloController {

	@Autowired
	private Task task;

	@RequestMapping("/")
	@ResponseBody
	public String Hello() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
		return "Hello World!";
	}
}
