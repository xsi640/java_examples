package com.suyang.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class MyStartupRunner1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println(Arrays.asList(args));
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作1<<<<<<<<<<<<<");
	}
}