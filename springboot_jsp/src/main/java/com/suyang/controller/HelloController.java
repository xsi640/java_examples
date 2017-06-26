package com.suyang.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	// 从 application.properties 中读取配置，如取不到默认值为Hello jsp
	@Value("${application.hello:Hello Angel}")
	private String hello;

	@RequestMapping("/helloJsp")
	public String helloJsp(Map<String, Object> map) {
		map.put("hello", hello);
		return "helloJsp";
	}
}
