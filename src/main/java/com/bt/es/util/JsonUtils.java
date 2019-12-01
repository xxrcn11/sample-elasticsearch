package com.bt.es.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonUtils {

	public static Map<String, Object> getMapFromJsonString(String content) throws JsonMappingException, JsonProcessingException {
		Map<String, Object> resultMap = null;
		resultMap = new ObjectMapper().readValue(content, HashMap.class);
		
		return resultMap;
		
	}
	
	public static String getStringFromObject(Object obj) {
		return new Gson().toJson(obj);
	}
	
	public static <T> T getObjectFromString(String content, Class<T> clazz) {
		return new Gson().fromJson(content, clazz);
	}
}
