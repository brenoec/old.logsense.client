package com.logsense.client.entities;

import com.logsense.client.LogSense;

public class ParameterEntity implements IEntity {
	
	private String name;
	private String type;
	private String value;

	@Override
	public String toJson() {
		return LogSense.gson.toJson(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
