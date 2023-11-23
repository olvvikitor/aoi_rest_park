package com.example.demoparkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoparkapi.dtos.ClientCreateDTO;
import com.example.demoparkapi.dtos.ClientResponseDTO;
import com.example.demoparkapi.entities.Client;
import com.example.demoparkapi.jwt.JwtUserDatails;
import com.example.demoparkapi.mapper.ClientMapper;
import com.example.demoparkapi.services.ClientService;
import com.example.demoparkapi.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@PostMapping
	@PreAuthorize("hasRole('CLIENT')")
	public ResponseEntity<ClientResponseDTO> createClient(@RequestBody @Valid ClientCreateDTO clientDto,
			@AuthenticationPrincipal JwtUserDatails userDetails){
		Client c = ClientMapper.toCient(clientDto);
		c.setUser(userService.findById(userDetails.getId()));
		clientService.create(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toDto(c));
	}
	

}
