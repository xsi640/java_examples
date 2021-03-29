package com.suyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.suyang.beans.SpringUtil;
import com.suyang.beans.TestBean;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SpringUtil springUtil() {
		return new SpringUtil();
	}
	
	@Bean
	public TestBean getBean(){
		return new TestBean();
	}
}
