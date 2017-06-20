package com.suyang.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=2)
public class MyStartupRunner2 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作2<<<<<<<<<<<<<");
	}
}