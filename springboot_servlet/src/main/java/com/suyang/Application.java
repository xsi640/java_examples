package com.suyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.suyang.servlet.MyServlet;

@SpringBootApplication
//自动扫描Servlet，如果实在当前类下注册的Servlet可以不用
@ServletComponentScan
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 注册my servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean getMyServlet() {
		return new ServletRegistrationBean(new MyServlet(), "/myServlet/*");
	}
}
