package cache.framework.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cache.framework.manager.CacheManager;
import cache.framework.rest.data.parser.CacheDataParser;

@Configuration
public class CacheServiceConfig {
	@Bean
	  public CacheDataParser getCacheDataParser(){
	    return new CacheDataParser();
	  }
	@Bean
	  public CacheManager getCacheManager(){
	    return new CacheManager();
	  }
}
