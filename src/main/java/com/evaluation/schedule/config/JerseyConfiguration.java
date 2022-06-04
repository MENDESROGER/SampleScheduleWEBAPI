package com.evaluation.schedule.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.evaluation.schedule.api.controller.CandidateResource;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;

import javax.ws.rs.ext.ContextResolver;


@Component
@ApplicationPath("/JAX-RS")
public class JerseyConfiguration extends ResourceConfig {
	
	@Bean
	public void swaggerConfig() {
	BeanConfig swaggerConfig = new BeanConfig();
        
	  swaggerConfig.setTitle("Swagger API Title");
	    swaggerConfig.setVersion("1.0.0");        
	   swaggerConfig.setSchemes(new String[] { "http" });
	    //swaggerConfig.setHost("localhost:8080/JAX-RS");
	    swaggerConfig.setBasePath("/");
	    swaggerConfig.setResourcePackage("com.evaluation.schedule.api.controller");
	    //swaggerConfig.setScan(true);    	   	  
	   // packages("com.evaluation.schedule.api.controller");
	         
	                  
	  SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);

	 /*packages(getClass().getPackage().getName(),
	                  ApiListingResource.class.getPackage().getName());*/
	 
	}
	
	public JerseyConfiguration(Class<?> ... classes) {
	 	
		 packages("com.evaluation.schedule.api.controller");
		 		 	 
	} 
 
}