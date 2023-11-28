package com.example.demoparkapi.test.vagas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.VagaCreateDto;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.test.jwt.JwtAuthentication;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "vagas-insert.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "vagas-delete.sql",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class VagasIT {


		
		@Autowired
		WebTestClient testVagas;
		
		@Test
		public void criar_vagas_com_perfil_valido_retorna_status_201() {	
			testVagas.post().uri("/api/v1/vagas")
			.contentType(MediaType.APPLICATION_JSON)
			.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
			.bodyValue(new VagaCreateDto ("06-B","LIVRE"))
			.exchange()
			.expectHeader().exists(org.springframework.http.HttpHeaders.LOCATION);
			}
		@Test
		public void criar_vagas_existente_com_perfil_valido_retorna_status_409() {	
			ErrorMessage error=
					testVagas.get().uri("/api/v1/vagas")
					.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456")) 
					.exchange().expectStatus().isEqualTo(409)
					.expectBody(ErrorMessage.class)
					.returnResult().getResponseBody();
			org.assertj.core.api.Assertions.assertThat(error).isNotNull();
			org.assertj.core.api.Assertions.assertThat(error.getStatus()).isEqualTo(409);
		}
}
