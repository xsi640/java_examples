package com.suyang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suyang.mq.Sender;

@Controller
public class HomeController {

	@Autowired
	private Sender sender;
	
	@RequestMapping("/")
	@ResponseBody
	public String index(){
		sender.send();
		return "Sended";
	}
}
