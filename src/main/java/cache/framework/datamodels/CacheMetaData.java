package cache.framework.datamodels;

import cache.framework.constants.CacheType;

public class CacheMetaData {
	private CacheType cacheType;
	private int size;
	public CacheType getCacheType() {
		return cacheType;
	}
	public void setCacheType(CacheType cacheType) {
		this.cacheType = cacheType;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
