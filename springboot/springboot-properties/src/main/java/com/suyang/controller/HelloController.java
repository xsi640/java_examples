package com.suyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suyang.settings.Settings;
import com.suyang.settings.TestSettings;

@Controller
public class HelloController {

	@Autowired
	private TestSettings testSettings;
	@Autowired
	private Settings settings;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "name:" + testSettings.getName() + " gender:" + testSettings.getGender();
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2() {
		return "name:" + settings.getName() + " age:" + settings.getAge();
	}
}
