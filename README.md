# CacheAsService

Current implementation supports LRU cache type only And String types only, but can be easily extended.

To add new cache type implement cache interface.

### To start cache as a service follow below steps:
- Go to cache/framework/rest/ and run `StartApplication.java`.
- This will start spring boot application at default port 8080.
- Go to browser/postman all apis are hosted at `localhost:8080`

### Currently 5 APIs are supported:
For easy usage you can copy `local cache as a service apis.postman_collection.json` and open it in postman to use below apis.

Cache index url: `localhost:8080/cache/`

Auth api to get valid unique auth-token for user:

	- API: GET `/v1/auth?custID=<Integer customer ID>`
	- Customer ID is current hardcoded so, pass ID from range [1001-1003].
	- Returns: unique token for custID.
	- example: GET localhost:8080/cache/v1/auth?custID=1001

Create cache for a customer:

	- API: POST `/v1/{custID}/cache`
	- Body params:
		- "token":<token got from auth api>
		- "size":<cache max size>
		- "cacheType": lru --currently only lru cache is supported.
	- returns: unique CacheID.
	- Example: localhost:8080/cache/v1/1001/cache
				body:{\n\t\"token\":\"abc24b\",\n\t\"size\":5,\n\t\"cacheType\":\"lru\"\n}"

To put value in cache:

	- API: POST `/v1/{custID}/cache/{cacheID}/put`
	- body params:
		- "token": user token
		- "key": key
		- "value": value
	- Returns: success or failure.
	- Example: POST localhost:8080/cache/v1/1001/cache/1/put
				 body:{\n\t\"token\":\"abc24b\",\n\t\"key\":\"snapshot1\",\n\t\"value\":\"version2\"\n}

To retrieve value from cache:
	
	- API: GET `/v1/{custID}/cache/{cacheID}/get?key=<key>&token=<token>`
	- example: localhost:8080/cache/v1/1001/cache/1/get?key=snapshot1&token=abc24b
	- Returns: input key and value. If value is not present it return null.

### Steps to use cache as a library:
- `cache.framework.core` contains all core cache logic.
- To use it as library either copy or add dependency in your project of this package.

##### To test functionality of core cache library please run `CacheFactoryTest.java` & `LRUCacheTest.java` placed under `/CacheAsService/src/test/java/cache/framework/core`
