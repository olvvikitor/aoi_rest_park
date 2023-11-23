package com.example.demoparkapi.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.UserCreateDTO;
import com.example.demoparkapi.dtos.UserPasswordDTO;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.exceptions.ErrorMessage;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "users-insert.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "users-delete.sql",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class UserIT {
	
	@Autowired
	WebTestClient testClient;
	
	@Test
	public void createUser_TestUsernameAndPassowrdValid_retornaUserAndStatus201() {
		// TODO Auto-generated method stub
		UserResponseDto user = (UserResponseDto) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("joaovj@gmail.com", "123456"))
				.exchange()
				.expectStatus().isCreated()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getUsername()).isEqualTo("joaovj@gmail.com");
		org.assertj.core.api.Assertions.assertThat(user.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getRole()).isEqualTo("CLIENT");
		

	}
	
	@Test
	public void createUser_TestUsernameInValid_retornaUserAndStatus422() {
		// TODO Auto-generated method stub
	ErrorMessage user = (ErrorMessage) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("","123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(422);
		
		user = (ErrorMessage) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("joao@gmail", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(422);
		
		user = (ErrorMessage) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("joao.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(422);
		
		
	}
	
	@Test
	public void createUser_TestPasswordInValid_retornaUserAndStatus422() {
		// TODO Auto-generated method stub
		ErrorMessage user = (ErrorMessage) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("victor@gmail.com",""))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(422);
		
		user = (ErrorMessage) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("joao@gmail", "12345"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(422);
		
		user = (ErrorMessage) testClient
				.post().uri("/api/v1/users")
				.bodyValue(new UserCreateDTO("joao.com", "12345678911234"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(422);
		
		
	}
	
	@Test
	public void FindUserById_TestBuscaUserPorId_retornaUserAndStatus200() {
		UserResponseDto user = (UserResponseDto) testClient
				.get().uri("/api/v1/users/01")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getUsername()).isEqualTo("joao@gmail.com");
		org.assertj.core.api.Assertions.assertThat(user.getRole()).isEqualTo("ADMIN");
		
		user = (UserResponseDto) testClient
				.get().uri("/api/v1/users/16")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getUsername()).isEqualTo("ana@gmail.com");
		org.assertj.core.api.Assertions.assertThat(user.getRole()).isEqualTo("CLIENT");
		
		user = (UserResponseDto) testClient
				.get().uri("/api/v1/users/16")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getUsername()).isEqualTo("ana@gmail.com");
		org.assertj.core.api.Assertions.assertThat(user.getRole()).isEqualTo("CLIENT");
		
	}
	
	@Test
	public void FindUserBYID_TestBuscaUserPorIdInvalid_retornaErrorMessageAndStatus404() {

		ErrorMessage user = (ErrorMessage) testClient
				.get().uri("/api/v1/users/0")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(404);
	}
	@Test
	public void FindUserBYID_TestBuscaUserPorIdInvalidClientFindClient_retornaErrorMessageAndStatus403() {
		
		ErrorMessage user = (ErrorMessage) testClient
				.get().uri("/api/v1/users/41")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
				.exchange()
				.expectStatus().isForbidden()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(403);
	}
	
	@Test
	public void UpdatePasswordById_TestUpdatePassword_retornaStringAndStatus200() {
		String msg = (String) testClient
				.patch().uri("/api/v1/users/16")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDTO("123456","1234567","1234567"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(msg).isNotNull();
		org.assertj.core.api.Assertions.assertThat(msg).isEqualTo("Senha alterada com sucesso!");	
		
		 msg = (String) testClient
				.patch().uri("/api/v1/users/01")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDTO("123456","1234567","1234567"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(msg).isNotNull();
		org.assertj.core.api.Assertions.assertThat(msg).isEqualTo("Senha alterada com sucesso!");	
	}
	
	@Test
	public void UpdatePasswordById_TestUpdatePasswordIsInvalidOrConfirmationIsDiferent_retornaMessageErrorAndStatus400() {
		ErrorMessage user = (ErrorMessage) testClient
				.patch().uri("/api/v1/users/01")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDTO("12345632","1234567","1234567"))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getMessage()).isEqualTo("Senha atual invalida, verifique e tente novamente");
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(400);
	}
	
	@Test
	public void UpdatePasswordById_TestIsInvalidOrConfirmationIsDiferent_retornaMessageErrorAndStatus400() {
			ErrorMessage user = (ErrorMessage) testClient
				.patch().uri("/api/v1/users/01")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDTO("123456","12345678","1234567"))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
			org.assertj.core.api.Assertions.assertThat(user).isNotNull();
			org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(400);
			org.assertj.core.api.Assertions.assertThat(user.getMessage()).isEqualTo("Senha de confirmaçao nao confere com a nova senha");	
			
		user = (ErrorMessage) testClient
				.patch().uri("/api/v1/users/01")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDTO("123456","1234567","12345678"))
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(400);
		org.assertj.core.api.Assertions.assertThat(user.getMessage()).isEqualTo("Senha de confirmaçao nao confere com a nova senha");	
	}	
	@Test
	public void UpdatePasswordByIdDiferent_TestIsInvalidId403() {

		ErrorMessage user = (ErrorMessage) testClient
				.patch().uri("/api/v1/users/0")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDTO("123456","123456","123456"))
				.exchange()
				.expectStatus().isForbidden()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
			org.assertj.core.api.Assertions.assertThat(user).isNotNull();
			org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(403);
			
				user = (ErrorMessage) testClient
			.patch().uri("/api/v1/users/0")
			.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(new UserPasswordDTO("123456","123456","123456"))
			.exchange()
			.expectStatus().isForbidden()
			.expectBody(ErrorMessage.class)
			.returnResult().getResponseBody();
	org.assertj.core.api.Assertions.assertThat(user).isNotNull();
	org.assertj.core.api.Assertions.assertThat(user.getStatus()).isEqualTo(403);
	} 
	
	@Test
	public void FindAllUsers_TestFIndAllUsers_retornaListUserAndStatus200() {
		List<UserResponseDto> user = (List<UserResponseDto>) testClient
				.get().uri("/api/v1/users")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "joao@gmail.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(UserResponseDto.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
		org.assertj.core.api.Assertions.assertThat(user.size()).isEqualTo(4);
		org.assertj.core.api.Assertions.assertThat(user.get(0).getUsername()).isEqualTo("joao@gmail.com");
		
	}
	@Test
	public void FindAllUsers_TestFindAllUsersNaoAutorizado_retornaErrorMessageAnd403() {
		List<UserResponseDto> user = (List<UserResponseDto>) testClient
				.get().uri("/api/v1/users")
				.headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@gmail.com", "123456"))
				.exchange()
				.expectStatus().isForbidden()
				.expectBodyList(UserResponseDto.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(user).isNotNull();
	}
}



