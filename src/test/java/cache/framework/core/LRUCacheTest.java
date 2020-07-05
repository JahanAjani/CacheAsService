package cache.framework.core;

import org.junit.Test;

import cache.framework.core.exceptions.CacheException;
import junit.framework.Assert;

public class LRUCacheTest {
	
	private Cache lruCache;
	
	@Test
	public void testLRUCacheCreationWithValidSize() {
		lruCache = new LRUCache(5);
		Assert.assertEquals("Capacity should be as expected.", 5, lruCache.getCapacity());
		Assert.assertEquals("Current size should be as expected.", 0, lruCache.getCurrentSize());	
		
	}
	
	@Test(expected = CacheException.class)
	public void testLRUCacheCreationWithInvalidSize() throws CacheException {
		lruCache = new LRUCache(0);
		lruCache.setData("snapshot", "version1");
	}
	
	@Test
	public void testLRUCacheFunctionality() throws CacheException {
		lruCache = new LRUCache(5);
		lruCache.setData("snapshot1", "version1");
		Assert.assertEquals("value should be as expected.", "version1", lruCache.getData("snapshot1"));
		lruCache.setData("snapshot2", "version2");
		Assert.assertEquals("value should be as expected.", null, lruCache.getData("snapshot5"));
		lruCache.setData("snapshot3", "version3");
		lruCache.setData("snapshot4", "version4");
		lruCache.setData("snapshot5", "version5");
		Assert.assertEquals("value should be as expected.", "version5", lruCache.getData("snapshot5"));
		lruCache.setData("snapshot6", "version6");
		Assert.assertEquals("value should be as expected.", null, lruCache.getData("snapshot1"));
		Assert.assertEquals("value should be as expected.", "version6", lruCache.getData("snapshot6"));
		Assert.assertEquals("value should be as expected.", "version2", lruCache.getData("snapshot2"));
		lruCache.setData("snapshot7", "version7");
		Assert.assertEquals("value should be as expected.", "version2", lruCache.getData("snapshot2"));
		Assert.assertEquals("value should be as expected.", null, lruCache.getData("snapshot3"));
		
		//update key's value and assert.
		lruCache.setData("snapshot7", "version8");
		Assert.assertEquals("value should be as expected.", "version8", lruCache.getData("snapshot7"));
		
	}

	@Test
	public void testLRUCacheFunctionalityWithSize1() throws CacheException {
		lruCache = new LRUCache(1);
		lruCache.setData("snapshot1", "version1");
		Assert.assertEquals("value should be as expected.", "version1", lruCache.getData("snapshot1"));
		lruCache.setData("snapshot2", "version2");
		Assert.assertEquals("value should be as expected.", null, lruCache.getData("snapshot1"));
		
	}
}
