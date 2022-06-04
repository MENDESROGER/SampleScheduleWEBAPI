package com.evaluation.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.evaluation.schedule"))             
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
	

		
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Roger Mendes", "roger_mendes@hotmail.com", "https://www.linkedin.com/in/roger-mendes-571a7960/");
		
			
	    return new ApiInfoBuilder()
	            .title("Simple Spring Boot API REST e REST WS SampleScheduleAPI")
	            .description("Agendamento de Exames de Certificação")
	            .description("Avaliação Técnica em Proceso Seletivo")
	            .version("1.0.0")
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	            .contact(contact)
	            .build();
	}

}
