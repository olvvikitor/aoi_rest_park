package com.example.demoparkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoparkapi.dtos.UserCreateDTO;
import com.example.demoparkapi.dtos.UserPasswordDTO;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.entities.User;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.mapper.UserMapper;
import com.example.demoparkapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "Contem todas as operaçoes relativas aos recursos para cadastro, edição e leitura de um usuário.")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Criar um novo usuario", description = "Recurso para criar um novo usuário", responses = {
			@ApiResponse(responseCode = "201", description = "recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "409", description = "Usuario e-mail ja existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
		@ApiResponse(responseCode = "422", description = "Recurso nao processado por entrada de dados invalida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDTO createDto) {
		User u = userService.createUser(UserMapper.toUser(createDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(u));
	}

	@Operation(summary = "Buscar um usuário por ID", description = "requisição exige um bearer token. acesso restrito admin ou cliente",security = @SecurityRequirement(name = "security"), responses = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "403", description = "Usuario sem permissão de acesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	@ApiResponse(responseCode = "404", description = "Recurso nao processado por id inexistente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))), })
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENT')) AND #id == authentication.principal.id")
	public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
		User u = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(u));
	}

	@Operation(summary = "Alteração de senha",  description = "requisição exige um bearer token. acesso restrito admin ou cliente", security = @SecurityRequirement(name = "security"),responses = {
			@ApiResponse(responseCode = "200", description = "Alteração feita com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "400", description = "Recurso nao processado por senha atual invalida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@ApiResponse(responseCode = "403", description = "Usuario sem permissão de acesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))), })
	@PatchMapping(value = "/{id}") // patch faz o update apenas de um parametro do objeto
	@PreAuthorize("hasAnyRole('ADMIN', 'CLIENT') AND #id == authentication.principal.id")
	public ResponseEntity<String> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDTO userDto) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.alterPassword(id, userDto.getSenhaAtual(), userDto.getNovaSenha(), userDto.getConfirmaSenha()));
	}

	@Operation(summary = "Busca por todos os usuarios", description = "requisição exige um bearer token. acesso restrito admin", security = @SecurityRequirement(name = "security"),responses = {
			@ApiResponse(responseCode = "403", description = "Usuario sem permissão de acesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))) })
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserResponseDto>> findAll() {
		List<User> u = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDto(u));
	}

}
