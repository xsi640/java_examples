package com.suyang.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.suyang.domain.MessagePackage;

@Controller
public class TestController {

	@GetMapping("/")
	public String index() {
		return "/index";
	}

	@MessageMapping("/send")
	@SendTo("/topic/msg")
	public MessagePackage send(MessagePackage message) throws Exception {
		System.out.println("receive msg:" + message.getMessage());
		return new MessagePackage(message.getMessage() + "...ok");
	}
}
