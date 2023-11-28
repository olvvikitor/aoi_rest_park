package com.example.demoparkapi.dtos;

public class VagaResponseDto {
	
	private Long id;
	private String codigo;
	private String status;
	
	public VagaResponseDto() {
		super();
	}

	public VagaResponseDto(Long id, String codigo, String status) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
