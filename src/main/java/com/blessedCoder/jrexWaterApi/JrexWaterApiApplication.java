package com.blessedCoder.jrexWaterApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "JWater Project API",
				version = "1.0.0",
				description = "This is a J rex water company project API",
				termsOfService = "Terms of Service",
				contact = @io.swagger.v3.oas.annotations.info.Contact(
						name = "Blessed Coder",
						email = "eddy@gmail.com"
				),
				license = @io.swagger.v3.oas.annotations.info.License(
						name = "License",
						url = "https://www.example.com/license"
				)
		)
)
public class JrexWaterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JrexWaterApiApplication.class, args);
	}

}
