package com.logsense.client;

import com.google.gson.Gson;

public class Json {

	private static final Gson gson = new Gson();

	public static String toJson(Object value) {
		return gson.toJson(value);
	}
}
