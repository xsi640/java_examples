package com.suyang.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
public class TemplateController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/hello")
	public String hello() {
		return "/hello";
	}

	@RequestMapping("/hello2")
	public String hello2() {
		System.out.println(messageSource.getMessage("welcome", null, new Locale("en", "US")));
		return "/hello";
	}

	@RequestMapping("/changeSessionLanauage")
	public String changeSessionLanauage(HttpServletRequest request, String lang) {

		System.out.println(lang);
		if ("zh".equals(lang)) {
			// 代码中即可通过以下方法进行语言设置
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("zh", "CN"));
		} else if ("en".equals(lang)) {
			// 代码中即可通过以下方法进行语言设置
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
					new Locale("en", "US"));
		}
		return "redirect:/hello";

	}
}
