package com.suyang.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {
	@RequestMapping("/hello")
	public String hello(Map<String, Object> map) {
		map.put("hello", "from TemplateController.hello use thymeleaf");
		return "/hello";
	}
}
