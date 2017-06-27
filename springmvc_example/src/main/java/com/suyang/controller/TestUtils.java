package com.suyang.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {
	
	private static TestUtils testUtils;
	
	@Autowired
	private TestService testService;
	
	@PostConstruct
	public void init(){
		testUtils = this;
		testUtils.testService = testService;
	}
	
	public static String test(String name){
		return testUtils.testService.hello(name);
	}
}
