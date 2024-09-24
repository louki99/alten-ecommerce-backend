package com.alten.ecommerce;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Alten E-commerce Simple Application REST API Documentation",
				description = "API endpoints for interacting with the Alten e-commerce application, designed to facilitate seamless online shopping experiences for users and companies.",
				version = "v1",
				contact = @Contact(
						name = "Alten Support Team",
						email = "support@alten.com",
						url = "https://www.alten.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.alten.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Alten E-commerce Microservice REST API Documentation",
				url = "https://www.alten-ecommerce.com/swagger-ui.html"
		)
)
public class EcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
