package com.suyang.controller;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	public String hello(String name) {
		return "Hello " + name;
	}
}
