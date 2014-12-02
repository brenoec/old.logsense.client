package com.logsense.client.entities.locations;

import com.logsense.client.entities.IEntity;
import com.logsense.client.entities.LocationEntity;
import com.logsense.client.entities.enums.LocationTypeEnum;

public class InnerLocationEntity extends LocationEntity implements IEntity {
	
	private String domain;
	
	private String element;
	
	private String routine;

	public InnerLocationEntity() {
		super.type = LocationTypeEnum.INNER;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getRoutine() {
		return routine;
	}

	public void setRoutine(String routine) {
		this.routine = routine;
	}
}
