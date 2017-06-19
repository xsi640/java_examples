package com.suyang.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest req, Exception e) {

		System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		return "SQLException";
	}
}
