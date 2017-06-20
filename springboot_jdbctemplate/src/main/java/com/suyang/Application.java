package com.suyang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Converter<String, Date> addNewConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

	@Autowired
	private Environment env;
	//
	// //使用了阿里巴巴的数据池管理
	// @Bean
	// public DataSource dataSource() {
	// DruidDataSource dataSource = new DruidDataSource();
	// dataSource.setUrl(env.getProperty("spring.datasource.url"));
	// dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
	// dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
	// dataSource.setInitialSize(2);
	// dataSource.setMaxActive(20);
	// dataSource.setMinIdle(0);
	// dataSource.setMaxWait(60000);
	// dataSource.setValidationQuery("SELECT 1");
	// dataSource.setTestOnBorrow(false);
	// dataSource.setTestWhileIdle(true);
	// dataSource.setPoolPreparedStatements(false);
	// return dataSource;
	// }

	//使用dbcp的数据池管理
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
}
