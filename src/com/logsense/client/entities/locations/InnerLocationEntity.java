package com.logsense.client.entities.locations;

import com.logsense.client.entities.IEntity;
import com.logsense.client.entities.LocationEntity;
import com.logsense.client.entities.enums.LocationTypeEnum;

public class InnerLocationEntity extends LocationEntity implements IEntity {

	private String section;

	private String element;

	private String routine;

	public InnerLocationEntity() {
		super.type = LocationTypeEnum.INNER;
	}

	public InnerLocationEntity(LocationEntity parent) {
		super(parent);
		super.type = LocationTypeEnum.INNER;
	}

	/**
	 * Returns a Location's section. A section is an abstraction to a subset in a project,
	 * like a folder, package or namespace, for instance.
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets a Location's section. A section is an abstraction to a subset in a project,
	 * like a folder, package or namespace, for instance.
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * Returns a Location's element. An element is an abstraction to an item in a domain,
	 * like a file, class, or interface, for instance.
	 */
	public String getElement() {
		return element;
	}

	/**
	 * Sets a Location's element. An element is an abstraction to an item in a domain,
	 * like a file, class, or interface, for instance.
	 */
	public void setElement(String element) {
		this.element = element;
	}

	/**
	 * Returns a Location's routine. A routine is an abstraction to a portion of an element,
	 * such as a method, a function or an aspect, for instance.
	 */
	public String getRoutine() {
		return routine;
	}

	/**
	 * Sets a Location's routine. A routine is an abstraction to a portion of an element,
	 * such as a method, a function or an aspect, for instance.
	 */
	public void setRoutine(String routine) {
		this.routine = routine;
	}
}
