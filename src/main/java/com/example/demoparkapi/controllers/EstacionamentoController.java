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

import com.example.demoparkapi.dtos.EstacionamentoCreateeDto;
import com.example.demoparkapi.dtos.EstacionamentoResponseDto;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.dtos.VagaResponseDto;
import com.example.demoparkapi.entities.ClientVaga;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.mapper.ClientVagaMapper;
import com.example.demoparkapi.services.ClientService;
import com.example.demoparkapi.services.ClientVagaService;
import com.example.demoparkapi.services.EstacionamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Tag(name = "Estacionameentos", description = "Operações de registro de entrada e saida de clientes do estacionamento")
@RestController
@RequestMapping("api/v1/estacionamentos")
public class EstacionamentoController {
	
	@Autowired
	EstacionamentoService estacionamentoService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	ClientVagaService clientVagaService;	

	@Operation(summary = "Inserir um cliente em uma vaga", description = "recurso para inserir cliente a vaga"+
			"Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'. ",
			security = @SecurityRequirement(name = "security"),
			responses = {
					@ApiResponse(responseCode = "201", description = "recurso criado com sucesso",headers = @Header(name = org.springframework.http.HttpHeaders.LOCATION),content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstacionamentoResponseDto.class))),
					@ApiResponse(responseCode = "403", description = "Recurso não processado, perfil invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
					@ApiResponse(responseCode = "404", description = "Causas Possiveis <br/>"
							+ "-CPF do cliente não cadastrado; <br/>"
							+ "-Nenhuma vaga livre;", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			     	@ApiResponse(responseCode = "422", description = "Recurso nao processado por entrada de dados invalida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
	@PostMapping("/check-in")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EstacionamentoResponseDto>checkin(@RequestBody @Valid EstacionamentoCreateeDto dto){
		ClientVaga clientVaga = ClientVagaMapper.toClientVaga(dto);
		clientVaga.setCliet(clientService.FindByCpf(dto.getClientCpf()));
		estacionamentoService.checkIn(clientVaga);
		EstacionamentoResponseDto responseDto = ClientVagaMapper.toDto(clientVaga);
		responseDto.setClientCpf(dto.getClientCpf());
		responseDto.setVagaCodigo(clientVaga.getVagas().getCodigo());
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{recibo}")
				.buildAndExpand(clientVaga.getRecibo())
				.toUri();
		return ResponseEntity.created(location).body(responseDto);
	}	
	
	@GetMapping("/check-in/{recibo}")
	@PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
	public ResponseEntity<EstacionamentoResponseDto>getByRecibo(@PathVariable String recibo){
		ClientVaga vaga = clientVagaService.findByRecibo(recibo);
		EstacionamentoResponseDto dto = ClientVagaMapper.toDto(vaga);
		return ResponseEntity.ok().body(dto);
	}
	
		
	

}
