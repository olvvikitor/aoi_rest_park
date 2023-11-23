package com.example.demoparkapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Configuration
public class SprinfDocOpenApiConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("security", securityScheme()))
				.info( 
						new Info().title("REST API - Spring Park")
						.description("API para gestao de estacionamento de veiculos")
						.version("v1")
						.license(new License().name("apache 1.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
						.contact(new Contact().name("Joao Victor Jesus De Oliveira").email("victoroliveira.3002@gmail.comm"))
						);
	}
	private SecurityScheme securityScheme() {
		return new SecurityScheme().description("insira um token valido para prosseguir").type(SecurityScheme.Type.HTTP).in(SecurityScheme.In.HEADER).scheme("bearer").bearerFormat("JWT").name("security");
	}

}
