package com.evaluation.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sun.faces.config.ConfigureListener;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.faces.webapp.FacesServlet;
@EnableSwagger2
@SpringBootApplication
public class SampleScheduleApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SampleScheduleApiApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	//web
	 @Bean
	    public ServletRegistrationBean facesServletRegistration() {
	        ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
	        registration.setLoadOnStartup(1);
	        registration.addUrlMappings("*.jr");
	        return registration;
	    }

	    @Bean
	    public ServletContextInitializer servletContextInitializer() {
	        return servletContext -> {
	            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	            servletContext.setInitParameter("primefaces.THEME", "redmond");
	        };
	    }

	    @Bean
	    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
	        return new ServletListenerRegistrationBean<>(new ConfigureListener());
	    }

		
}
