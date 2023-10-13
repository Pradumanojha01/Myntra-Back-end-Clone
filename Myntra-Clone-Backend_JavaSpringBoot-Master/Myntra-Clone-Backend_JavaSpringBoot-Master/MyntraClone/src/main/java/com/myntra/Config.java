package com.myntra;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableScheduling
public class Config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.myntra.controller")).paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false);
	}

	@SuppressWarnings("deprecation")
	private ApiInfo getInfo() {
		return new ApiInfo("Myntra Clone : JAVA Backend API Documentation",
				"API Documentation for Myntra Clone JAVA Backend", "1.0.1", ".", ".", ".", ".");
	}

}
