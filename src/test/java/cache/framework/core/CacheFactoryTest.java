package cache.framework.core;

import org.junit.Test;

import cache.framework.core.constants.CacheType;
import cache.framework.core.datamodels.CacheMetaData;
import junit.framework.Assert;

public class CacheFactoryTest {

	private Cache lruCache;
	
	@Test
	public void testGetLRUCacheInstance() {
		CacheFactory cacheFactory = new CacheFactory();
		CacheMetaData cacheMetaData = new CacheMetaData(CacheType.LRU, 5);
		lruCache = cacheFactory.getCacheInstance(cacheMetaData);
		Assert.assertEquals("Should be instance of LRUCache", true, lruCache instanceof LRUCache);
	}

}
