package cache.framework.core.manager;

import cache.framework.core.Cache;
import cache.framework.core.CacheFactory;
import cache.framework.core.constants.CacheType;
import cache.framework.core.datamodels.CacheMetaData;
import cache.framework.core.exceptions.CacheException;
import cache.framework.rest.data.CacheInputData;

public class CacheManager {
	
	//creating single instance with default size at first.
	private Cache cache;
	
	
	public CacheManager() {
		//default it for now.
		//read it from separated Rest API
		CacheFactory cacheFactory = new CacheFactory();
		CacheMetaData cacheMetaData = new CacheMetaData(CacheType.LRU, 5);
		cache = cacheFactory.getCacheInstance(cacheMetaData);
	}
	
	public boolean put(CacheInputData cacheInputData) {
		boolean success;
		try {
			cache.setData(cacheInputData.getKey(), cacheInputData.getValue());
			System.out.println("called set on cache. for key: "+cacheInputData.getKey());
			success = true;
		} catch (CacheException e) {
			System.out.println("for key: "+ cacheInputData.getKey() +" "+ e.getMessage());
			success = false;
		}
		return success;
	}
	
	public String get(CacheInputData cacheInputData) {
		String value = null;
		value = cache.getData(cacheInputData.getKey());
		System.out.println("called get on cache. for key: "+cacheInputData.getKey() + " result value is: "+ value);
		return value;
	}
}
