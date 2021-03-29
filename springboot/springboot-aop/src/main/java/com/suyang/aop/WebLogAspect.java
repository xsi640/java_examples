package com.suyang.aop;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 实现Web层的日志切面
 * 
 */

@Aspect
@Component
@Order(-5)
public class WebLogAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	/**
	 * 定义一个切入点
	 *
	 * ~ 第1个 * 代表任意修饰符及任意返回值. 
	 * ~ 第2个 * 任意包名 
	 * ~ 第3个 * 代表任意方法. 
	 * ~ .. 匹配任意数量的参数.
	 */
	@Pointcut("execution(public * com.suyang.controller.*.*(..))")
	public void webLog() {
	}

	/**
	 * 切入方法之前
	 * @param joinPoint
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		 startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		logger.info("WebLogAspect.doBefore()");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

		// 获取所有参数
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			System.out.println(paraName + ": " + request.getParameter(paraName));
		}
	}

	/**
	 * 切入方法之后
	 * @param joinPoint
	 */
	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
		logger.info("WebLogAspect.doAfterReturning()");
		logger.info("耗时（毫秒） : " + (System.currentTimeMillis()- startTime.get()));
	}
}
