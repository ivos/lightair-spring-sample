package com.github.ivos.lightairspringsample.utils;

import java.net.URI;

public class RestUtils {

	public static URI location(Long id) {
		return URI.create(String.valueOf(id));
	}
}
