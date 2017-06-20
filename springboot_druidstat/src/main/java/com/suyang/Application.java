package com.suyang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@SpringBootApplication
@ServletComponentScan
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
	
	/** 
	 * 注册一个StatViewServlet 
	 * @return 
	 */  
	@Bean  
	public ServletRegistrationBean DruidStatViewServle(){  
	    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");  
	  
	    //添加初始化参数：initParams  
	    /** 白名单，如果不配置或value为空，则允许所有 */  
	    servletRegistrationBean.addInitParameter("allow","127.0.0.1,192.0.0.1");  
	    /** 黑名单，与白名单存在相同IP时，优先于白名单 */  
	    servletRegistrationBean.addInitParameter("deny","192.0.0.1");  
	    /** 用户名 */  
	    servletRegistrationBean.addInitParameter("loginUsername","admin");  
	    /** 密码 */  
	    servletRegistrationBean.addInitParameter("loginPassword","admin");  
	    /** 禁用页面上的“Reset All”功能 */  
	    servletRegistrationBean.addInitParameter("resetEnable","false");  
	    return servletRegistrationBean;  
	}  
	  
	/** 
	 * 注册一个：WebStatFilter 
	 * @return 
	 */  
	@Bean  
	public FilterRegistrationBean druidStatFilter(){  
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());  
	  
	    /** 过滤规则 */  
	    filterRegistrationBean.addUrlPatterns("/*");  
	    /** 忽略资源 */  
	    filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");  
	    return filterRegistrationBean;  
	}  
}
