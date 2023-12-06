package com.example.demoparkapi.test.estacionamentos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.EstacionamentoCreateeDto;
import com.example.demoparkapi.dtos.VagaCreateDto;
import com.example.demoparkapi.test.jwt.JwtAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "estacionamentos-insert.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "estacionamentos-delete.sql",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class EstacionamentoIT {

	@Autowired
	WebTestClient testVagas;
	
	@Test
	public void check_in_client_no_estacionamento_dados_corretos_retorna_status_201() {	
		EstacionamentoCreateeDto createDto = EstacionamentoCreateeDto.builder()
				.placa("WER-2231")
				.marca("HONDA")
				.modelo("CIVIC 1998")
				.cor("AMARELO")
				.clientCpf("92340493064")
				.build();
		testVagas.post().uri("api/v1/estacionamentos/check-in")
		.contentType(MediaType.APPLICATION_JSON)
		.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
		.bodyValue(createDto)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().exists(HttpHeaders.LOCATION)
		.expectBody().jsonPath("placa").isEqualTo("WER-2231")
		.jsonPath("marca").isEqualTo("HONDA")
		.jsonPath("modelo").isEqualTo("CIVIC 1998")
		.jsonPath("cor").isEqualTo("AMARELO")
		.jsonPath("clientCpf").isEqualTo("92340493064")
		.jsonPath("recibo").exists()
		.jsonPath("dataEntrada").exists()
		.jsonPath("vagaCodigo").exists();
		}
	
	@Test
	public void check_in_com_dados_sem_permimissao_retorna_status_403() {
		EstacionamentoCreateeDto createDto = EstacionamentoCreateeDto.builder()
				.placa("WER-2231")
				.marca("HONDA")
				.modelo("CIVIC 1998")
				.cor("AMARELO")
				.clientCpf("92340493064")
				.build();
		testVagas.post().uri("/api/v1/estacionamentos/check-in")
		.contentType(MediaType.APPLICATION_JSON)
		.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "ana@gmail.com", "123456"))
		.bodyValue(createDto)
		.exchange()
		.expectStatus().isForbidden()
		.expectBody()
		.jsonPath("status").isEqualTo(403)
		.jsonPath("method").isEqualTo("POST");
	}

	@Test
	public void check_in_com_dados_ivalidos_retorna_status_422() {
		EstacionamentoCreateeDto createDto = EstacionamentoCreateeDto.builder()
				.placa("WER-231")//placa invalida
				.marca("HONDA")
				.modelo("CIVIC 1998")
				.cor("AMARELO")
				.clientCpf("92340493064")
				.build();
		testVagas.post().uri("/api/v1/estacionamentos/check-in")
		.contentType(MediaType.APPLICATION_JSON)
		.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
		.bodyValue(createDto)
		.exchange()
		.expectStatus().isEqualTo(422)
		.expectBody()
		.jsonPath("status").isEqualTo(422)
		.jsonPath("method").isEqualTo("POST");
		
		 createDto = EstacionamentoCreateeDto.builder()
					.placa("WER-2231")
					.marca("HONDA")
					.modelo("CIVIC 1998")
					.cor("AMARELO")
					.clientCpf("92340493")//Cpf invalido
					.build();
			testVagas.post().uri("/api/v1/estacionamentos/check-in")
			.contentType(MediaType.APPLICATION_JSON)
			.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
			.bodyValue(createDto)
			.exchange()
			.expectStatus().isEqualTo(422)
			.expectBody()
			.jsonPath("status").isEqualTo(422)
			.jsonPath("method").isEqualTo("POST");
			
	}
	@Test
	public void check_in_com_dados_inexistentes_retorna_status_404() {
		EstacionamentoCreateeDto createDto = EstacionamentoCreateeDto.builder()
				.placa("WER-2231")
				.marca("HONDA")
				.modelo("CIVIC 1998")
				.cor("AMARELO")
				.clientCpf("91706336063")//CPF NÃO EXISTE NO BANCO
				.build();
		testVagas.post().uri("/api/v1/estacionamentos/check-in")
		.contentType(MediaType.APPLICATION_JSON)
		.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
		.bodyValue(createDto)
		.exchange()
		.expectStatus().isNotFound()
		.expectBody()
		.jsonPath("status").isEqualTo(404)
		.jsonPath("method").isEqualTo("POST");
		
	
}
	@Sql(scripts = "estacionamentos-ocupadas-insert.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = "estacionamentos-ocupadas-delete.sql",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void check_in_com_vagas_inexistentes_retorna_status_404() {
		EstacionamentoCreateeDto createDto = EstacionamentoCreateeDto.builder()
				.placa("WER-2231")
				.marca("HONDA")
				.modelo("CIVIC 1998")
				.cor("AMARELO")
				.clientCpf("92340493064")
				.build();
		testVagas.post().uri("/api/v1/estacionamentos/check-in")
		.contentType(MediaType.APPLICATION_JSON)
		.headers(JwtAuthentication.getHeaderAuthorization(testVagas, "joao@gmail.com", "123456"))
		.bodyValue(createDto)
		.exchange()
		.expectStatus().isNotFound()
		.expectBody()
		.jsonPath("status").isEqualTo(404)
		.jsonPath("method").isEqualTo("POST");
	}
		
	}
