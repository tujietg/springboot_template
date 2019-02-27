package com.tujietg.model;

import java.util.HashMap;
import java.util.Map;

public class DataSource {
	private static Map<String, String> hashMap = new HashMap<String, String>();
	static {
		hashMap.put("lili", "lili123");
		hashMap.put("admin", "admin");
	}

	public static Map getDate() {
		return hashMap;
	}
}
