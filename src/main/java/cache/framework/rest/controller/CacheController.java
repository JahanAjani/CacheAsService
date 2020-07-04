package cache.framework.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cache.framework.manager.CacheManager;
import cache.framework.rest.BaseResponse;
import cache.framework.rest.CacheGetResponse;
import cache.framework.rest.data.CacheInputData;
import cache.framework.rest.data.parser.CacheDataParser;

@RestController
@RequestMapping("/cache")
public class CacheController {
 
	@Autowired
	private CacheDataParser cacheDataParser;
	
	@Autowired
	private CacheManager cacheManager;
	
	
	@RequestMapping(value = "/put", method = RequestMethod.POST, produces = "application/json")
	public BaseResponse put(@RequestBody String cacheInput) { 
		BaseResponse response;
		
		CacheInputData cacheInputData = cacheDataParser.parse(cacheInput);
		response = getPostResponse(cacheInputData);
		 
		return response;
	}

	
	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	public CacheGetResponse get(@RequestParam(value = "key") String key) { 
		CacheGetResponse response;
		
		CacheInputData cacheInputData = new CacheInputData();
		cacheInputData.setKey(key);
		response = getGetResponse(cacheInputData);
		 
		return response;
	}


	private CacheGetResponse getGetResponse(CacheInputData cacheInputData) {
		CacheGetResponse res = new CacheGetResponse();
		
		String value = cacheManager.get(cacheInputData);
		res.setKey(cacheInputData.getKey());
		res.setValue(value);
		return res;
	}


	private BaseResponse getPostResponse(CacheInputData cacheInputData) {
		BaseResponse res = new BaseResponse();
		
		res.setSuccess(cacheManager.put(cacheInputData));
		
		return res;
	}
}