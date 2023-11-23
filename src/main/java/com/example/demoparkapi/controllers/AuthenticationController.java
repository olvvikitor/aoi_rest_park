package com.example.demoparkapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoparkapi.dtos.UserLoginDTO;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.exceptions.ErrorMessage;
import com.example.demoparkapi.jwt.JwtToken;
import com.example.demoparkapi.jwt.JwtUserDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Tag(name = "Autenticação", description = "Recurso para proceder com a autenticação da api")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
	private static Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private final JwtUserDetailsService detailsService;

	@Autowired
	private final AuthenticationManager authenticationManager;
	
	
	public AuthenticationController(JwtUserDetailsService detailsService, AuthenticationManager authenticationManager) {
		super();
		this.detailsService = detailsService;
		this.authenticationManager = authenticationManager;
	}


	@Operation(summary = "Autenticar na api", description = "Recurso de utenticação na api", responses = {
			@ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso e retorno de um bearer token", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Credenciais invalidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@ApiResponse(responseCode = "422", description = "Recurso nao processado por entrada de dados invalida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	@PostMapping("/auth")
	public ResponseEntity<?> autenticar(@RequestBody @Valid UserLoginDTO dto, HttpServletRequest request){
		log.info("processo de autenticaçao pelo login{}", dto.getUsername());
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
			authenticationManager.authenticate(authenticationToken);
			JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());
			return ResponseEntity.ok(token);
		}catch(AuthenticationException e) {
			log.warn("Bad Cretendials from usename {}", dto.getUsername());;
		}
		return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,"credenciais invalidas"));
		
	}
	

}
