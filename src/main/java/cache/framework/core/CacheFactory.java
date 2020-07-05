package cache.framework.core;

import cache.framework.core.constants.CacheType;
import cache.framework.core.datamodels.CacheMetaData;

public class CacheFactory {
	
	public Cache getCacheInstance(CacheMetaData cacheMetaData) {
		if(CacheType.LRU.equals(cacheMetaData.getCacheType())){
			return new LRUCache(cacheMetaData.getSize());
		}
		return null;
	}
}
