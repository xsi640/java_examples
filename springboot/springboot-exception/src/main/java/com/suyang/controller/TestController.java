package com.suyang.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		int i = 100/0;
		return "error";
	}
	

	@RequestMapping("/testsql")
	@ResponseBody
	public String testSql() throws SQLException{
		throw new SQLException();
	}
}
