package com.example.demoparkapi.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "cpf", nullable = false, unique = true, length = 11)
	private String cpf;
	
	@OneToOne
	@JoinColumn(name= "id_user", nullable = false)
	private User user;
	
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
	
	
	
	


	public Client() {
		super();
	}

	

	public Client(Long id, String name, String cpf, LocalDateTime dataCriacao, LocalDateTime dataModificacao,
			String criadoPor, String modificadoPor, User user) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
		this.criadoPor = criadoPor;
		this.modificadoPor = modificadoPor;
		this.user = user;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
