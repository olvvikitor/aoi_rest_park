package com.example.demoparkapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.dtos.VagaCreateDto;
import com.example.demoparkapi.dtos.VagaResponseDto;
import com.example.demoparkapi.entities.Vaga;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.mapper.VagaMapper;
import com.example.demoparkapi.services.VagaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Vagas", description = "recurso para criação e monitoramento de vagas")
@RestController
@RequestMapping("api/v1/vagas")
public class VagaController {
	 
	@Autowired
	VagaService vagaService;
	
	
	@Operation(summary = "Criar uma nova vaga", description = "recurso para criar uma nova vaga"+
			"Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'. ",
			security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "201", description = "recurso criado com sucesso",headers = @Header(name = org.springframework.http.HttpHeaders.LOCATION),content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
					@ApiResponse(responseCode = "403", description = "Recurso não processado, perfil invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "409", description = "vaga ja existe no sistema", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			     	@ApiResponse(responseCode = "422", description = "Recurso nao processado por entrada de dados invalida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> createVaga(@RequestBody @Valid VagaCreateDto vaga) {
		Vaga v = VagaMapper.toVaga(vaga);
		vagaService.create(v);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(vaga.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
	}
	@Operation(summary = "Busca uma vaga por codigo da vaga", description = "recurso para buscar vaga por codigo. "+
			"Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'. ",
			   security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VagaResponseDto.class))),
					@ApiResponse(responseCode = "403", description = "Recurso não processado, perfil invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			     	@ApiResponse(responseCode = "404", description = "Recurso nao processado, codigo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
	@GetMapping("{codigo}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VagaResponseDto> getByCodigo(@PathVariable String codigo) {
		Vaga v = vagaService.findByCodigo(codigo);
		return ResponseEntity.ok().body(VagaMapper.toDto(v));
	}

}
