package com.logsense.client.entities;

import java.util.List;

import com.logsense.client.LogSense;

public class InteractionEntity implements IEntity {
	
	private String name;
	private String description;
	private String status;
	
	private List<LocationEntity> locations;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LocationEntity> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationEntity> locations) {
		this.locations = locations;
	}
}
