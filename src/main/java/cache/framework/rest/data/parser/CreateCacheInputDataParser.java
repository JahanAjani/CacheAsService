package cache.framework.rest.data.parser;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cache.framework.rest.data.CreateCacheInputData;

public class CreateCacheInputDataParser {

	public CreateCacheInputDataParser() {
		
	}
	
	public CreateCacheInputData parse(String createCacheInput) {
		Gson gson = new Gson();
		@SuppressWarnings("serial")
	    Type type = new TypeToken<CreateCacheInputData>() {}.getType();
	    return gson.fromJson(createCacheInput, type);
	}
}
