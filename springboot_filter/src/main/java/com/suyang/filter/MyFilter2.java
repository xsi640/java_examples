package com.suyang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter2 implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器初始化,from bean");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("执行过滤操作,from bean");
        chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("过滤器销毁,from bean");
	}

}
