package com.suyang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suyang.domain.User;

@RestController
public class UserController {

	@RequestMapping("/")
	public User get(){
		User u = new User();
		u.setId(1);
		u.setName("1231231");
		return u;
	}
}
