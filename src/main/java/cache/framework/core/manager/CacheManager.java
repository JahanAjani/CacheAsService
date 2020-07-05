package cache.framework.core.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import cache.framework.core.Cache;
import cache.framework.core.CacheFactory;
import cache.framework.core.constants.CacheType;
import cache.framework.core.datamodels.CacheMetaData;
import cache.framework.core.exceptions.CacheException;
import cache.framework.rest.data.CacheInputData;
import cache.framework.rest.data.CreateCacheInputData;

public class CacheManager {
	
	private static AtomicInteger cacheID = new AtomicInteger(0);
	private Map<Integer, Map<Integer, Cache>> customerToCacheMap;
	
	public CacheManager() {
		
		customerToCacheMap = new ConcurrentHashMap<Integer, Map<Integer,Cache>>();
		//default it for now.
		//read it from separated Rest API
		
		//		CacheMetaData cacheMetaData = new CacheMetaData(CacheType.LRU, 5);
		//		cache = cacheFactory.getCacheInstance(cacheMetaData);
	}
	
	public Integer creatCache(CreateCacheInputData input) {
		CacheType type = getCacheType(input.getCacheType());
		if(type == null) {
			return null;
		}
		CacheMetaData cacheMetaData = new CacheMetaData(type, input.getSize());
		Cache cache = CacheFactory.getCacheInstance(cacheMetaData);
		Map<Integer, Cache> storedCache = customerToCacheMap.getOrDefault(input.getCustomerID(), new HashMap<>());
		Integer cacheID = this.getCacheId();
		storedCache.put(cacheID, cache);
		customerToCacheMap.put(input.getCustomerID(), storedCache);
		return cacheID;
	}
	private Integer getCacheId() {
		return cacheID.incrementAndGet();
	}

	private CacheType getCacheType(String cacheType) {
		CacheType c = null; 
		if(cacheType.equalsIgnoreCase("lru")) {
			c = CacheType.LRU;
		}
		return c;
	}

	public boolean put(CacheInputData cacheInputData) throws CacheException {
		Map<Integer, Cache> storedCache = customerToCacheMap.get(cacheInputData.getCustID());
		if(storedCache == null) {
			throw new CacheException("Invalid custID: "+ cacheInputData.getCacheID());
		}
		Cache cache = storedCache.get(cacheInputData.getCacheID());
		if(cache == null) {
			throw new CacheException("Invalid cacheID: "+ cacheInputData.getCacheID());
		}
		cache.setData(cacheInputData.getKey(), cacheInputData.getValue());
		System.out.println("called set on cache. for key: "+cacheInputData.getKey());
		return true;
	}
	
	public String get(CacheInputData cacheInputData) throws CacheException {
		String value = null;
		Map<Integer, Cache> storedCache = customerToCacheMap.get(cacheInputData.getCustID());
		if(storedCache == null) {
			throw new CacheException("Invalid custID: "+ cacheInputData.getCacheID());
		}
		Cache cache = storedCache.get(cacheInputData.getCacheID());
		if(cache == null) {
			throw new CacheException("Invalid cacheID: "+ cacheInputData.getCacheID());
		}
		value = cache.getData(cacheInputData.getKey());
		System.out.println("called get on cache. for key: "+cacheInputData.getKey() + " result value is: "+ value);
		return value;
	}
}
