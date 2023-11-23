package com.example.demoparkapi.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.UserLoginDTO;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.jwt.JwtToken;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "users-insert.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "users-delete.sql",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class AuthenticacaoIT {
	
	@Autowired
	WebTestClient testClient;
	
	@Test
	public void authenticar_com_credenciais_validas_retorna_token_status_200() {
	JwtToken response =	testClient
		.post()
		.uri("/api/v1/auth")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(new UserLoginDTO("ana@gmail.com", "123456"))
		.exchange()
		.expectStatus().isOk()
		.expectBody(JwtToken.class)
		.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		
	}
	@Test
	public void authenticar_com_credenciais_invalidas_retorna_errorMessage_status_400() {
		ErrorMessage response =	testClient
				.post()
				.uri("/api/v1/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserLoginDTO("invalid@gmail.com", "123456"))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(400);
		
	    response =	testClient
		.post()
		.uri("/api/v1/auth")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(new UserLoginDTO("ana@gmail.com", "000000"))
		.exchange()
		.expectStatus().isBadRequest()
		.expectBody(ErrorMessage.class)
		.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(400);
		
		
	}
	@Test
	public void authenticar_com_entrada_dados_retorna_errorMessage_status_400() {
		//Email invalido
		ErrorMessage response =	testClient
				.post()
				.uri("/api/v1/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserLoginDTO("invalid@gmail", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);
		
		//Senha menor que 6
		response =	testClient
				.post()
				.uri("/api/v1/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserLoginDTO("ana@gmail.com", "0000"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);
		
		//Senha maior que 13
		response =	testClient
				.post()
				.uri("/api/v1/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserLoginDTO("ana@gmail.com", "00000000000000"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(response).isNotNull();
		org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);
		
		
	}
	

}
