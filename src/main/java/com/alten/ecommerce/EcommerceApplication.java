package com.alten.ecommerce;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Service Rcc microservice REST API Documentation",
				description = "API endpoints for interacting with the Service Rcc microservice within the iMDAE platform, which provides request certificates from PKI.",
				version = "v1",
				contact = @Contact(
						name = "Teams digiup",
						email = "contact@gmail.com",
						url = "https://www.imdae.org"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.imdae.org"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Service Rcc microservice REST API Documentation",
				url = "https://www.imdae.org/swagger-ui.html"
		)
)
public class EcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
