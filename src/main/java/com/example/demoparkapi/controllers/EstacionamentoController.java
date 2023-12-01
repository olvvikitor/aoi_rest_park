package com.example.demoparkapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demoparkapi.dtos.EstacionamentoCreateeDto;
import com.example.demoparkapi.dtos.EstacionamentoResponseDto;
import com.example.demoparkapi.entities.ClientVaga;
import com.example.demoparkapi.mapper.ClientVagaMapper;
import com.example.demoparkapi.services.ClientService;
import com.example.demoparkapi.services.EstacionamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/estacionamentos")
public class EstacionamentoController {
	
	@Autowired
	EstacionamentoService estacionamentoService;
	
	@Autowired
	ClientService clientService;
	
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
	

}
