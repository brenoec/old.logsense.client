package com.logsense.client;

import java.util.Stack;

import com.logsense.client.entities.InteractionEntity;
import com.logsense.client.entities.LocationEntity;

public class InteractionBuilder {

	public static Stack<String> stack = new Stack<String>();

	private static InteractionEntity interaction = new InteractionEntity();
	private static LocationEntity currentLocation = null;

	public static boolean configure(String name, String description) {
		InteractionBuilder.getInteraction().setName(name);
		InteractionBuilder.getInteraction().setDescription(description);

		return true;
	}

	public LocationEntity findLocation(LocationEntity location) {
		return null;
	}

	public static InteractionEntity getInteraction() {
		return InteractionBuilder.interaction;
	}

	public static LocationEntity getCurrentLocation() {
		return InteractionBuilder.currentLocation;
	}

	public static void setCurrentLocation(LocationEntity currentLocation) {
		InteractionBuilder.currentLocation = currentLocation;
	}
}
