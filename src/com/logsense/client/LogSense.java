package com.logsense.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.logsense.client.entities.IEntity;

public class LogSense {

	private static String url;

	private static String system = null;
	private static String solution = null;
	private static String component = null;

	public static boolean configure(String url) {
		LogSense.url = url;

		return true;
	}

	public static boolean configure(String url, String system, String solution, String project) {
		LogSense.url = url;

		LogSense.system = system;
		LogSense.solution = solution;
		LogSense.component = project;

		return true;
	}

	public static HttpResponse log(IEntity value) throws ClientProtocolException, IOException {
		StringEntity json = new StringEntity("{\"interaction\":" + Json.toJson(value) + "}");

		System.out.println("[LogSense][information]\t" + "{\"interaction\":" + Json.toJson(value) + "}");

		HttpPost post = new HttpPost(url);
		post.setEntity(json);
		post.setHeader("Content-type", "application/json");

		HttpClient client = HttpClients.createDefault();

		HttpResponse response = null;

		try {
			response = client.execute(post);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode < 200 || statusCode >= 300) {
				System.out.println("[LogSense][warning]\t" + response.getStatusLine());
			}
		} catch (HttpHostConnectException e) {
			System.out.println("[LogSense][error]\t" + e.getMessage());
		}

		return response;
	}

	public static String getUrl() {
		return url;
	}

	public static String getSystem() {
		return system;
	}

	public static String getSolution() {
		return solution;
	}

	public static String getComponent() {
		return component;
	}
}
