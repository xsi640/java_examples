package com.suyang.schedule;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleTest {

	//5秒執行一次
	@Scheduled(cron = "0/5 * * * * ?")
	public void test() {
		System.out.println(">>>>>>>>> time:" + new Date());
	}
}
