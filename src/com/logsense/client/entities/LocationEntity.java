package com.logsense.client.entities;

import java.util.List;

import com.logsense.client.LogSense;
import com.logsense.client.entities.enums.LocationTypeEnum;

public abstract class LocationEntity implements IEntity {

	private String system;
	private String solution;

	private String name;

	protected LocationTypeEnum type;

	private List<ParameterEntity> inputs;
	private List<ParameterEntity> outputs;

	private List<LocationEntity> locations;

	@Override
	public String toJson() {
		return LogSense.gson.toJson(this);
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationTypeEnum getType() {
		return type;
	}

	public List<ParameterEntity> getInputs() {
		return inputs;
	}

	public void setInputs(List<ParameterEntity> inputs) {
		this.inputs = inputs;
	}

	public List<ParameterEntity> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<ParameterEntity> outputs) {
		this.outputs = outputs;
	}

	public List<LocationEntity> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationEntity> locations) {
		this.locations = locations;
	}
}
