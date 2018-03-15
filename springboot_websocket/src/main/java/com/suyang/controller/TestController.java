package com.suyang.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suyang.domain.MessagePackage;

@Controller
@EnableScheduling
public class TestController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@GetMapping("/")
	public String index() {
		return "/index";
	}

	@MessageMapping("/send")
	@SendTo("/topic/send")
	public MessagePackage send(MessagePackage message) throws Exception {
		System.out.println("receive msg:" + message.getMessage());
		return new MessagePackage(message.getMessage() + "...ok");
	}

	@Scheduled(fixedRate = 1000)
	@SendTo("/topic/callback")
	public Object callback() throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpMessagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
		return "callback";
	}
}
