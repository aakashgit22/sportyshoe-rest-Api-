package com.simplilearn.sportyshoe.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConfigurationProperties("app.api")
@ConditionalOnProperty(name="app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfig {

	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				 .host("http://localhost:8999")
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.simplilearn.sportyshoe"))
			.paths(PathSelectors.any())
			.build()
			.directModelSubstitute(LocalDate.class, java.sql.Date.class)
			.directModelSubstitute(LocalDateTime.class, java.util.Date.class)
			.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("sportyshoe API")
			.description("Documentation sportyshoe api")
			.version("1.0.0")
			
			.build();
	}

	/**
		...
		...
		Getters
		Setters
		...
		...
	**/
}