package com.suyang.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suyang.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class HelloServiceTest {
	@Autowired
	private HelloService helloService;

	@Test
	public void testGetName() {
		Assert.assertEquals("hello", helloService.getName());
	}
}
