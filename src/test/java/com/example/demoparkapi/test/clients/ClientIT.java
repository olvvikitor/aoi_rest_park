package com.example.demoparkapi.test.clients;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.ClientCreateDTO;
import com.example.demoparkapi.dtos.ClientResponseDTO;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.test.jwt.JwtAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "clients-insert.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "clients-delete.sql",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class ClientIT {
	
	
	@Autowired
	WebTestClient testClient;
	
	@Test
	public void criar_cliente_com_dados_validos_retorna_cliente_status_201() {
		ClientResponseDTO client=
		testClient.post().uri("/api/v1/clients")
		.contentType(MediaType.APPLICATION_JSON)
		.headers(JwtAuthentication.getHeaderAuthorization(testClient, "tody@gmail.com", "123456"))
		.bodyValue(new ClientCreateDTO("Tody silva","24567600002"))
		.exchange().expectStatus().isCreated()
		.expectBody(ClientResponseDTO.class)
		.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getName()).isEqualTo("Tody silva");
		org.assertj.core.api.Assertions.assertThat(client.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getCpf()).isEqualTo("24567600002");
		
	}
	@Test
	public void criar_cliente_com_dados_invalidos_cpf_existente_retorna_error_status_409() {
		ErrorMessage client=
				testClient.post().uri("/api/v1/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "tody@gmail.com", "123456"))
				.bodyValue(new ClientCreateDTO("tody silva","92340493064"))//cpf da ana. ja existe 
				.exchange().expectStatus().isEqualTo(409)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(409);
		
	}
	public void criar_cliente_com_dados_invalidos_perfil_admin_retorna_error_status_403() {
		ErrorMessage client=
				testClient.post().uri("/api/v1/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))//tentanto com perfil de admin
				.bodyValue(new ClientCreateDTO("Tody silva","82106574029"))
				.exchange().expectStatus().isForbidden()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(403);
		
	}
	public void criar_cliente_com_dados_invalidos_cpf_invalido_retorna_error_status_422() {
		ErrorMessage client=
				testClient.post().uri("/api/v1/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.bodyValue(new ClientCreateDTO("Tody silva","821065740"))//campo cpf invalido
				.exchange().expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(422);
		org.assertj.core.api.Assertions.assertThat(client.getMessage()).isEqualTo("formato de campo invalido, por favor insira um campo valido");
		
		client=
				testClient.post().uri("/api/v1/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.bodyValue(new ClientCreateDTO("",""))//os dois campos invalidos
				.exchange().expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(422);
		org.assertj.core.api.Assertions.assertThat(client.getMessage()).isEqualTo("formato de campo invalido, por favor insira um campo valido");
		
		client=
				testClient.post().uri("/api/v1/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.bodyValue(new ClientCreateDTO("Tody ","821.065.740-29"))//nome menor que o aceitavel e cpf invalido
				.exchange().expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(422);
		org.assertj.core.api.Assertions.assertThat(client.getMessage()).isEqualTo("formato de campo invalido, por favor insira um campo valido");
		
	}

	@Test
	public void buscar_cliente_com_dados_validos_retorna_client_status_200() {
		ClientResponseDTO client=
				testClient.get().uri("/api/v1/clients/10")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456")) 
				.exchange().expectStatus().isOk()
				.expectBody(ClientResponseDTO.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getName()).isEqualTo("ana Silva");
		org.assertj.core.api.Assertions.assertThat(client.getCpf()).isEqualTo("92340493064");
	}
	@Test
	public void buscar_cliente_com_dados_invalidos_id_inexistente_retorna_error_status_404() {
		ErrorMessage client=
				testClient.get().uri("/api/v1/clients/0")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456")) 
				.exchange().expectStatus().isNotFound()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(404);
		
			}
	
	@Test
	public void buscar_cliente_com_dados_perfil_nao_autorizado_retorna_error_status_403() {
		ErrorMessage client=
				testClient.get().uri("/api/v1/clients/10")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456")) 
				.exchange().expectStatus().isEqualTo(403)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(403);	
	}

	@Test
	public void buscar_todos_clientes_perfil_valido_retorna_lista_clientes_status_200() {
		List<ClientResponseDTO> user = testClient
				.get().uri("/api/v1/clients")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(ClientResponseDTO.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.get(0).getName()).isEqualTo("ana Silva");
		org.assertj.core.api.Assertions.assertThat(user.size()).isEqualTo(2);
		
	}
	@Test
	public void buscar_todos_clientes_perfil_ivalido_retorna_lista_error_status_403() {
		ErrorMessage client=
				testClient.get().uri("/api/v1/clients/10")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456")) 
				.exchange().expectStatus().isEqualTo(403)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(client).isNotNull();
		org.assertj.core.api.Assertions.assertThat(client.getStatus()).isEqualTo(403);	
	}
}
