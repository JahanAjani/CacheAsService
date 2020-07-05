package cache.framework.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cache.framework.core.exceptions.CacheException;
import cache.framework.core.manager.CacheManager;
import cache.framework.rest.CacheGetResponse;
import cache.framework.rest.CreateCacheResponse;
import cache.framework.rest.PutCacheResponse;
import cache.framework.rest.auth.AuthManager;
import cache.framework.rest.auth.datamodels.AuthResponse;
import cache.framework.rest.data.CacheInputData;
import cache.framework.rest.data.CreateCacheInputData;
import cache.framework.rest.data.parser.CacheDataParser;
import cache.framework.rest.data.parser.CreateCacheInputDataParser;

@RestController
@RequestMapping("/cache")
public class CacheController {
 
	@Autowired
	private AuthManager authManager;
	@Autowired
	private CacheDataParser cacheDataParser;
	@Autowired
	private CreateCacheInputDataParser createCacheInputDataParser;
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public String index() { 
		return "Welcome to cache service.\nplease use apis to talk with me.\nFor available apis and their usage refer to README.md";
	}
	
	@RequestMapping(value = "/v1/auth", method = RequestMethod.GET, produces = "application/json")
	public AuthResponse auth(@RequestParam(value = "custID") Integer customerID) { 
		AuthResponse response;
		
		response = getAuthResponse(customerID);
		 
		return response;
	}
	
	@RequestMapping(value = "/v1/{custID}/cache", method = RequestMethod.POST, produces = "application/json")
	public CreateCacheResponse createCachePerUser(@PathVariable(value = "custID") Integer customerID, @RequestBody String createCacheInput) { 
		CreateCacheResponse response;
		response = getCreateCachePerUserResponse(customerID, createCacheInput); 
		return response;
	}

	@RequestMapping(value = "/v1/{custID}/cache/{cacheID}/put", method = RequestMethod.POST, produces = "application/json")
	public PutCacheResponse put(@RequestBody String cacheInput, @PathVariable(value="custID") Integer custID, @PathVariable(value="cacheID") Integer cacheID) { 
		PutCacheResponse response;
		
		CacheInputData cacheInputData = cacheDataParser.parse(cacheInput);
		cacheInputData.setCustID(custID);
		cacheInputData.setCacheID(cacheID);
		response = getPostResponse(cacheInputData);
		return response;
	}

	
	@RequestMapping(value = "/v1/{custID}/cache/{cacheID}/get", method = RequestMethod.GET, produces = "application/json")
	public CacheGetResponse get(@RequestParam(value = "key") String key, @PathVariable(value="custID") Integer custID, @PathVariable(value="cacheID") Integer cacheID, @RequestParam String token) { 
		CacheGetResponse response;
		
		CacheInputData cacheInputData = new CacheInputData();
		cacheInputData.setKey(key);
		cacheInputData.setToken(token);
		cacheInputData.setCustID(custID);
		cacheInputData.setCacheID(cacheID);
		response = getGetResponse(cacheInputData);
		 
		return response;
	}

	private CreateCacheResponse getCreateCachePerUserResponse(Integer customerID, String createCacheInput) {
		//validate token, then create cache with cache manager.
		CreateCacheResponse res = new CreateCacheResponse();
		CreateCacheInputData input = createCacheInputDataParser.parse(createCacheInput);
		input.setCustomerID(customerID);
		if(authManager.isValid(customerID, input.getToken())) {
			res.setCacheID(cacheManager.creatCache(input));
			res.setSuccess(true);
		} else {
			res.setCacheID(null);
			res.setSuccess(false);
		}
		return res;
	}
	
	private CacheGetResponse getGetResponse(CacheInputData cacheInputData) {
		CacheGetResponse res = new CacheGetResponse();
		
		if(!authManager.isValid(cacheInputData.getCustID(), cacheInputData.getToken())) {
			res.setKey(null);
			res.setValue("invalid auth-token for custID "+ cacheInputData.getCustID());
			return res;
		}
		String value;
		try {
			value = cacheManager.get(cacheInputData);
		} catch (CacheException e) {
			System.out.println(e.getMessage());
			value = e.getMessage();
		}
		res.setKey(cacheInputData.getKey());
		res.setValue(value);
		return res;
	}


	private PutCacheResponse getPostResponse(CacheInputData cacheInputData) {
		PutCacheResponse res = new PutCacheResponse();
		
		if(!authManager.isValid(cacheInputData.getCustID(), cacheInputData.getToken())) {
			res.setSuccess(false);
			res.setMessage("invalid auth-token for custID "+ cacheInputData.getCustID());
			return res;
		}
		
		try {
			res.setSuccess(cacheManager.put(cacheInputData));
			res.setMessage("inserted");
			res.setSuccess(true);
		} catch (CacheException e) {
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	private AuthResponse getAuthResponse(Integer customerID) {
		String token = authManager.auth(customerID);
		AuthResponse authResponse = new AuthResponse();
		if(token  != null) {
			authResponse.setAuthToken(token);
			authResponse.setSuccess(true);
		}
		return authResponse;
	}
}