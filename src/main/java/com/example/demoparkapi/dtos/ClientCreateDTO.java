package com.example.demoparkapi.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientCreateDTO {
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String name;
	
	@NotBlank
	@Size(min = 11, max = 11)
	@CPF
	private String cpf;

	public ClientCreateDTO(@NotBlank @Size(min = 5, max = 100) String name,
			@NotBlank @Size(min = 11, max = 11) @CPF String cpf) {
		super();
		this.name = name;
		this.cpf = cpf;
	}

	public ClientCreateDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
