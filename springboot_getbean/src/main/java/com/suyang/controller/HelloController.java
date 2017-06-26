package com.suyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suyang.beans.SpringUtil;
import com.suyang.beans.TestBean;

@Controller
public class HelloController {

	@Autowired
	private TestBean testBean;

	@RequestMapping("/hello")
	@ResponseBody
	public String Hello() {
		return SpringUtil.getBean(TestBean.class).getName();
	}

	@RequestMapping("/hello2")
	@ResponseBody
	public String Hello2() {
		return testBean.getName() + " by autowired";
	}
}
