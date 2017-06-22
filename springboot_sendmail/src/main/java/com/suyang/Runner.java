package com.suyang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("412887952@qq.com");//发送者.
        message.setTo("1473773560@qq.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容");//邮件内容.
        mailSender.send(message);//发送邮件
	}
}
