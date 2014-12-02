package com.logsense.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.logsense.client.entities.IEntity;

public class LogSense {

	private String url;

	public static final Gson gson = new Gson();

	public LogSense(String url) {
		this.url = url;
	}
	
	public HttpResponse log(IEntity value) throws ClientProtocolException, IOException {
		StringEntity json = new StringEntity("{ \"interaction\" : " + gson.toJson(value) + " }");
		
		System.out.println("[LogSense][information]\t" + "{ \"interaction\" : " + gson.toJson(value) + " }");
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
