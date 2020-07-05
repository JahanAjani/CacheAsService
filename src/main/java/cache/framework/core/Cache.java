package cache.framework.core;

import cache.framework.core.exceptions.CacheException;

public interface Cache {
	public String getData(String key);
	public void setData(String key, String value) throws CacheException;
	public int getCapacity();
	public int getCurrentSize();
}
