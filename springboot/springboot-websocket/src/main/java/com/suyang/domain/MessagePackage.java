package com.suyang.domain;

import java.io.Serializable;
import java.util.Date;

public class MessagePackage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Date time;

	public MessagePackage() {
	}

	public MessagePackage(String message) {
		this(message, new Date());
	}

	public MessagePackage(String message, Date time) {
		this.message = message;
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
