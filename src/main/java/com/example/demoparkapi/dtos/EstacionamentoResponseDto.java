package com.example.demoparkapi.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)//não incluir campos nulos na resposta do json
public class EstacionamentoResponseDto {

	private String placa;
	private String marca;
	private String modelo;
	private String cor;
	private String clientCpf;
	private String recibo;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")//formatação do Json de datas
	private LocalDateTime dataEntrada;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime dataSaida;
	private String vagaCodigo;
	private BigDecimal valor;
	private BigDecimal desconto;
	
	
	public EstacionamentoResponseDto() {
		super();
	}

	


	public EstacionamentoResponseDto(String clientCpf,String placa, String marca, String modelo, String cor,
			String recibo, LocalDateTime dataEntrada, LocalDateTime dataSaida, String vagaCodigo, BigDecimal valor,
			BigDecimal desconto) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.clientCpf = clientCpf;
		this.recibo = recibo;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.vagaCodigo = vagaCodigo;
		this.valor = valor;
		this.desconto = desconto;
	}




	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getClientCpf() {
		return clientCpf;
	}


	public void setClientCpf(String clientCpf) {
		this.clientCpf = clientCpf;
	}


	public String getRecibo() {
		return recibo;
	}


	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}


	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public LocalDateTime getDataSaida() {
		return dataSaida;
	}


	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}


	public String getVagaCodigo() {
		return vagaCodigo;
	}


	public void setVagaCodigo(String vagaCodigo) {
		this.vagaCodigo = vagaCodigo;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public BigDecimal getDesconto() {
		return desconto;
	}


	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	

	
}
