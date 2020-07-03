package cache.framework.core;

import cache.framework.constants.CacheType;
import cache.framework.datamodels.CacheMetaData;

public class CacheFactory {
	
	public Cache getCacheInstance(CacheMetaData cacheMetaData) {
		if(CacheType.LRU.equals(cacheMetaData.getCacheType())){
			return new LRUCache(cacheMetaData.getSize());
		}
		return null;
	}
}
