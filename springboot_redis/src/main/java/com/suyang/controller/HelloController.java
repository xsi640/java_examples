package com.suyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		//add key value
		redisTemplate.opsForValue().set("test", "the test value in redis.");
		return "saved";
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2() {
		//get value
		return redisTemplate.opsForValue().get("test");
	}
}
