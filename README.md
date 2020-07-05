# CacheAsService
Espressif Systems coding assignment.

Current implementation supports LRU cache type only.

To add new cache type implement cache interface.

### To start cache as a service follow below steps:
- Go to cache/framework/rest/ and run `StartApplication.java`.
- This will start spring boot application at default port 8080.
- Go to browser/postman all apis are hosted at `localhost:8080/cache/`

