package com.suyang.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 没有order注解，最后执行 
 * @author xsi64
 *
 */
@Component
public class MyStartupRunner1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println(Arrays.asList(args));
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作1<<<<<<<<<<<<<");
	}
}