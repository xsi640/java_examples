package com.suyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
		// add key value
		redisTemplate.opsForValue().set("test", "the test value in redis.");
		return "saved";
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2() {
		// get value
		return redisTemplate.opsForValue().get("test");
	}

	@RequestMapping("/test3")
	@ResponseBody
	@Cacheable(value = "test3_cache", keyGenerator = "wiselyKeyGenerator")
	public String test3() {
		System.out.println("无缓存的时候调用这里");
		return "test3 cache";
	}
}
