package com.suyang.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class User {

	private int id;
	@JSONField(serialize = false)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
