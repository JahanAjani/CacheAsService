package cache.framework.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cache.framework.rest.config.CacheServiceConfig;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(CacheServiceConfig.class);

		appContext.registerShutdownHook();

		appContext.scan("com.expressifs.cache.*");
      
		appContext.start();
		SpringApplication.run(StartApplication.class, args);

	}

}
