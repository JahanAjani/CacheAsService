# CacheAsService
Espressif Systems coding assignment.

Current implementation supports LRU cache type only And String types only, but can be easily extended.

To add new cache type implement cache interface.

### To start cache as a service follow below steps:
- Go to cache/framework/rest/ and run `StartApplication.java`.
- This will start spring boot application at default port 8080.
- Go to browser/postman all apis are hosted at `localhost:8080`

### Currently supported API and usage:
1. Auth api to get valid unique auth-token for user:

	- API: `/cache/v1/auth?custID=<Integer customer ID>`
	- Customer ID is current hardcoded so, pass ID from range [1001-1003].
	
2. To add value to cache:

	- API: `/cache/set`
	- body params:
		- "key": key
		- "value": value
3. To retrieve value from cache:
	
	- API: `/cache/get?key=<pass key here>`
	-example: /cache/get?key=snapshot1


### Steps to use cache as a library:
- `cache.framework.core` contains all core cache logic.
- To use it as library either copy or add dependency in your project of this package.

#####To test functionality of core cache library please run `CacheFactoryTest.java` & `LRUCacheTest.java` placed under `/CacheAsService/src/test/java/cache/framework/core`