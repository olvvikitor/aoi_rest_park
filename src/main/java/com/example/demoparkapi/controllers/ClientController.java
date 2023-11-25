package com.example.demoparkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoparkapi.dtos.ClientCreateDTO;
import com.example.demoparkapi.dtos.ClientResponseDTO;
import com.example.demoparkapi.dtos.PageableDto;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.entities.Client;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.jwt.JwtUserDatails;
import com.example.demoparkapi.mapper.ClientMapper;
import com.example.demoparkapi.mapper.PageableMapper;
import com.example.demoparkapi.repositories.projection.ClientProjection;
import com.example.demoparkapi.services.ClientService;
import com.example.demoparkapi.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name= "Clientes", description = "Contem todas as operações ao recurso de um cliente")
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@Operation(summary = "Criar um novo cliente", description = "recurso para criar um novo cliente vinculdo ao um usuariocadastrado. "+
	"Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENT'. ",
	responses = {
			@ApiResponse(responseCode = "201", description = "recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "409", description = "CPF ja existeno sistema", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@ApiResponse(responseCode = "403", description = "Recurso não permitido a esse tipo de perfil", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	     	@ApiResponse(responseCode = "422", description = "Recurso nao processado por entrada de dados invalida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
	@PostMapping
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<ClientResponseDTO> createClient(@RequestBody @Valid ClientCreateDTO clientDto,
			@AuthenticationPrincipal JwtUserDatails userDetails){
		Client c = ClientMapper.toCient(clientDto);
		c.setUser(userService.findById(userDetails.getId()));
		clientService.create(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toDto(c));
	}
	
	@Operation(summary = "Busca um cliente por id (Apenas admin  pode fazer a busca)", description = "recurso Buscar um cliente por id. "+
			"Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'. ",
			responses = {
					@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
					@ApiResponse(responseCode = "403", description = "Recurso não permitido a esse tipo de perfil", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			     	@ApiResponse(responseCode = "404", description = "Recurso nao processado, Id não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
	@GetMapping(value= "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id){
		Client c = clientService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ClientMapper.toDto(c));
	}
	
	@Operation(summary = "Busca todos os clientes (Apenas admin  pode fazer a busca)", description = "recurso Buscar todos os clientes. "+
		   "Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'. ",
			responses = {
					@ApiResponse(responseCode = "403", description = "Recurso não permitido a esse tipo de perfil", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class)))})
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageableDto> findAll(Pageable pageable){
		Page<ClientProjection> list = clientService.findAll(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(PageableMapper.toDto(list));
	}
	

}
