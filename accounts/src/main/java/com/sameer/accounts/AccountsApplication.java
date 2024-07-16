package com.sameer.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
info = @Info(
		title = "Accounts microservice REST API Documentation",
		description = "HeadsUpBank Accounts microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
				name = "Sumit Kumar",
				email = "sumit@headsupcorporation.com",
				url = "https://headsupcorporation.com/"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://headsupcorporation.com/"
		)
),
externalDocs = @ExternalDocumentation(
		description =  "HeadsUpBank Accounts microservice REST API Documentation",
		url = "https://headsupcorporation.com/swagger-ui.html"
)
)

public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
