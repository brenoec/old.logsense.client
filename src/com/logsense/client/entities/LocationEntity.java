package com.logsense.client.entities;

import java.util.ArrayList;
import java.util.List;

import com.logsense.client.Json;
import com.logsense.client.entities.enums.LocationTypeEnum;

/**
 * @author Breno Souza
 */
public abstract class LocationEntity implements IEntity {

	private String system;
	private String solution;

	private String component;

	protected LocationTypeEnum type;

	private List<ParameterEntity> inputs;
	private List<ParameterEntity> outputs;

	private transient LocationEntity parent;

	private List<LocationEntity> locations;

	public LocationEntity() {
		this.parent = null;
		this.locations = new ArrayList<LocationEntity>();
	}

	public LocationEntity(LocationEntity parent) {
		this.parent = parent;
		this.locations = new ArrayList<LocationEntity>();
	}

	@Override
	public String toJson() {
		return Json.toJson(this);
	}

	@Override
	public String toString() {
		return toJson();
	}

	/**
	 * Returns a String that is a system's name which has modules
	 * to deliver a complete service or product.
	 *
	 * @return A system's name
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * Sets a system's name which has modules to deliver a service or product.
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * Returns a String that is a solution's name. A module which provides
	 * functionalities through reunion of submodules.
	 *
	 * @return A solution's name
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * Sets a solution's name. A module which provides
	 * functionalities through reunion of submodules.
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 * Returns a String that is the component name.
	 * A component could represent a project or an external system, for instance.
	 *
	 * @return A submodule's name
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * Sets a component's name. A component could represent a project or an external system, for instance.
	 */
	public void setComponent(String name) {
		this.component = name;
	}

	/**
	 * Returns a Location's type, which could be:
	 * <ul>
	 * 	<li>A Special location</li>
	 *	<li>An Inner location</li>
	 *	<li>An Outer location</li>
	 * </ul>
	 *
	 * @see LocationTypeEnum
	 */
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

	public LocationEntity getParent() {
		return parent;
	}

	public void setParent(LocationEntity parent) {
		this.parent = parent;
	}

	public List<LocationEntity> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationEntity> locations) {
		this.locations = locations;
	}

	public void addLocation(LocationEntity location) {
		this.locations.add(location);
	}
}
