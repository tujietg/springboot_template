package com.tujietg.model;

import java.util.HashMap;
import java.util.Map;

public class DataSource {
	private static Map<String, Map<String, String>> hashMap = new HashMap<String, Map<String, String>>();
	static {
		Map<String, String> data1 = new HashMap<String, String>();
		data1.put("role", "admin");
		data1.put("password", "lili123");
		hashMap.put("lili", data1);

		Map<String, String> data2 = new HashMap<String, String>();
		data2.put("role", "user");
		data2.put("password", "sss123");
		hashMap.put("sss", data2);
	}

	public static Map getData() {
		return hashMap;
	}
}
