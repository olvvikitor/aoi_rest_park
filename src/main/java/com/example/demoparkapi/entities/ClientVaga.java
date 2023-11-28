package com.example.demoparkapi.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes_tem_vagas")
@EntityListeners(AuditingEntityListener.class)
public class ClientVaga implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_recibo", nullable = false, unique = true, length = 15)
	private String recibo;
	
	@Column(name = "placa", nullable = false, length = 8)
	private String placa;
	@Column(name = "marca", nullable = false, length = 15)
	private String marca;
	@Column(name = "modelo", nullable = false, length = 15)
	private String modelo;
	@Column(name = "cor", nullable = false, length = 15)
	private String cor;
	@Column(name = "data_entrada", nullable = false)
	private LocalDateTime dataEntrada;
	@Column(name = "data_saida", nullable = true)
	private LocalDateTime dataSaida;
	@Column(name = "valor", nullable = true, columnDefinition = "decimal(7,2)")
	private BigDecimal valor;	
	@Column(name = "valor", nullable = true, columnDefinition = "decimal(7,2)")
	private BigDecimal desconto;
	
	@ManyToOne
	@JoinColumn(name="id_client", nullable = false)
	private Client cliet;
	@ManyToOne
	@JoinColumn(name="id_vaga", nullable = false)
	private Vaga vagas;
	
	// @CreatedDate é usado para popular automaticamente o campo anotado com a data e hora atuais quando uma entidade é persistida.
	@CreatedDate
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	// @LastModifiedDate é usado para popular automaticamente o campo anotado com a última data e hora modificada.
	@LastModifiedDate
	@Column(name = "data_modificacao")
	private LocalDateTime dataModificacao;

	// @CreatedBy é usado para popular automaticamente o campo anotado com o usuário que criou a entidade.
	@CreatedBy
	@Column(name = "criado_por")
	private String criadoPor;

	// @LastModifiedBy é usado para popular automaticamente o campo anotado com o usuário que modificou pela última vez a entidade.
	@LastModifiedBy
	@Column(name = "modificado_por")
	private String modificadoPor;

	public ClientVaga(Long id, String recibo, String placa, String marca, String modelo, String cor,
			LocalDateTime dataEntrada, LocalDateTime dataSaida, BigDecimal valor, BigDecimal desconto, Client cliet,
			Vaga vagas, LocalDateTime dataCriacao, LocalDateTime dataModificacao, String criadoPor,
			String modificadoPor) {
		super();
		this.id = id;
		this.recibo = recibo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.desconto = desconto;
		this.cliet = cliet;
		this.vagas = vagas;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
		this.criadoPor = criadoPor;
		this.modificadoPor = modificadoPor;
	}

	public ClientVaga() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
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

	public Client getCliet() {
		return cliet;
	}

	public void setCliet(Client cliet) {
		this.cliet = cliet;
	}

	public Vaga getVagas() {
		return vagas;
	}

	public void setVagas(Vaga vagas) {
		this.vagas = vagas;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public String getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientVaga other = (ClientVaga) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
