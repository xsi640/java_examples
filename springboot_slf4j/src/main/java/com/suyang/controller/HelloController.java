package com.suyang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	@ResponseBody
	public String Hello(@RequestParam(name = "s", required = false) String s) {
		logger.info(s);
		return "Hello World!";
	}
}
