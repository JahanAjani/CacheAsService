package cache.framework.core.datamodels;

import cache.framework.core.constants.CacheType;

public class CacheMetaData {
	private CacheType cacheType;
	private int size;
	
	public CacheMetaData(CacheType cacheType, int size) {
		this.cacheType = cacheType;
		this.size = size;
	}
	
	public CacheType getCacheType() {
		return cacheType;
	}
	
	public int getSize() {
		return size;
	}

}
