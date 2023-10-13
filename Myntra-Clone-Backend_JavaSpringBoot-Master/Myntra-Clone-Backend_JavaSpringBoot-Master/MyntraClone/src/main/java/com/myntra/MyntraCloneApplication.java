package com.myntra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
@EnableWebMvc
@EnableSwagger2
public class MyntraCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyntraCloneApplication.class, args);
	}

}
