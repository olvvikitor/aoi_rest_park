package com.example.demoparkapi.test.vagas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.VagaCreateDto;
import com.example.demoparkapi.dtos.VagaResponseDto;
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
		public void criar_vagas_que_ja_existe_com_perfil_valido_retorna_status_409() {	
			testVagas.post().uri("/api/v1/vagas")
			.contentType(MediaType.APPLICATION_JSON)
			.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
			.bodyValue(new VagaCreateDto ("01-A","LIVRE"))
			.exchange()
			.expectStatus().isEqualTo(409)
			.expectBody()
			.jsonPath("status").isEqualTo(409)
			.jsonPath("method").isEqualTo("POST");
			}
		
		@Test
		public void criar_vagas_dados_invalidos_perfil_valido_retorna_status_422() {	
			testVagas.post().uri("/api/v1/vagas")
			.contentType(MediaType.APPLICATION_JSON)
			.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
			.bodyValue(new VagaCreateDto ("01-UI","DESOCUPADO"))
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody()
			.jsonPath("status").isEqualTo(422)
			.jsonPath("method").isEqualTo("POST");
		}
		@Test
		public void buscar_vagas_com_dados_validos() {	
			VagaResponseDto vaga = testVagas.get().uri("/api/v1/vagas/01-A")
			.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
			.exchange()
			.expectStatus().isOk()
			.expectBody(VagaResponseDto.class)
			.returnResult().getResponseBody();
			
			org.assertj.core.api.Assertions.assertThat(vaga.getId()).isEqualTo(1);
			org.assertj.core.api.Assertions.assertThat(vaga.getCodigo()).isEqualTo("01-A");			
		}
		
		@Test
		public void criar_vagas_dados_invalidos_perfil_valido_retorna_status_404() {	
			testVagas.get().uri("/api/v1/vagas/0-A")
			.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
			.exchange()
			.expectStatus().isEqualTo(404)
			.expectBody()
			.jsonPath("status").isEqualTo(404)
			.jsonPath("method").isEqualTo("GET");
		}
}
