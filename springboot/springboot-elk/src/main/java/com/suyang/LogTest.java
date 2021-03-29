package com.suyang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class LogTest {

    private Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Scheduled(fixedRate = 2000)
    public void logtest() {
        logger.trace("日志trace");
        logger.debug("日志debug");
        logger.info("日志info");
        logger.warn("日志warn");
        logger.error("日志error");
    }

}
