package com.suyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "Hello world!";
	}
	
	@RequestMapping("/test1")
	@ResponseBody
	public String test1(){
		return "Hello world111!";
	}
}
