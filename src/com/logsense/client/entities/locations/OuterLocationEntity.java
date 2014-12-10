package com.logsense.client.entities.locations;

import com.logsense.client.entities.IEntity;
import com.logsense.client.entities.LocationEntity;
import com.logsense.client.entities.enums.LocationTypeEnum;

public class OuterLocationEntity extends LocationEntity implements IEntity {

	private String description;

	public OuterLocationEntity() {
		super.type = LocationTypeEnum.OUTER;
	}

	public OuterLocationEntity(LocationEntity parent) {
		super(parent);
		super.type = LocationTypeEnum.OUTER;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
