package com.example.demoparkapi.dtos;

public class ClientResponseDTO {

	private Long id;
	private String name;
	private String cpf;
	
	
	public ClientResponseDTO() {
		super();
	}


	public ClientResponseDTO(Long id, String name, String cpf) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
