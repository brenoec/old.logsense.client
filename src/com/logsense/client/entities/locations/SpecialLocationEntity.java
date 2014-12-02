package com.logsense.client.entities.locations;

import com.logsense.client.entities.IEntity;
import com.logsense.client.entities.LocationEntity;
import com.logsense.client.entities.enums.LocationTypeEnum;

public class SpecialLocationEntity extends LocationEntity implements IEntity {

	private String description;

	public SpecialLocationEntity() {
		super.type = LocationTypeEnum.SPECIAL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
