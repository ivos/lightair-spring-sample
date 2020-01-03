package com.github.ivos.lightairspringsample.utils;

import org.springframework.http.HttpHeaders;

import java.net.URI;

public class RestUtils {

	public static HttpHeaders eTag(Long version) {
		HttpHeaders headers = new HttpHeaders();
		headers.setETag("\"" + version + "\"");
		return headers;
	}

	public static URI location(Long id) {
		return URI.create(String.valueOf(id));
	}
}
