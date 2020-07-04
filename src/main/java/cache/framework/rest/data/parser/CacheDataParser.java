package cache.framework.rest.data.parser;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cache.framework.rest.data.CacheInputData;

public class CacheDataParser {
	
	public CacheDataParser() {
	}
	
	public CacheInputData parse(String cacheInputData) {
		Gson gson = new Gson();
		@SuppressWarnings("serial")
	    Type type = new TypeToken<CacheInputData>() {}.getType();
	    return gson.fromJson(cacheInputData, type);
	}
	
}
