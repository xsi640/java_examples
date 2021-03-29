package com.suyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.suyang.filter.MyFilter2;

@SpringBootApplication
@ServletComponentScan
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilter(){
		FilterRegistrationBean registration = new FilterRegistrationBean();  
        registration.setFilter(new MyFilter2());  
        registration.addUrlPatterns("/*");  
        return registration;  
	}
}
