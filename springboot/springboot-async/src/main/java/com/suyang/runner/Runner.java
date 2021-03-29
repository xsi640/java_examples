package com.suyang.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.suyang.Task;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private Task task;
	
	@Override
	public void run(String... args) throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}

}
