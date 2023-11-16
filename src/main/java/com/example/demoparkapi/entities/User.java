package com.example.demoparkapi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.example.demoparkapi.enuns.TypesUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;


	@Column(name = "username",nullable = false, length = 100, unique = true)
	private String username;

	@Column(name = "password", nullable = false,length = 200)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false,length = 25)
	private TypesUser role = TypesUser.ROLE_CLIENT;

	@Column(name = "data_criacao")
	private LocalDate dataCriacao;

	@Column(name = "data_modificacao")
	private LocalDate dataModificacao;

	@Column(name = "criado_por")
	private String criadoPor;

	@Column(name = "modificado_por")
	private String modificadoPor;
	

	public User() {
		super();
	}

	

	public User(Long id, String username, String password, TypesUser role, LocalDate dataCriacao,
			LocalDate dataModificacao, String criadoPor, String modificadoPor) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.dataCriacao = dataCriacao;
		this.dataModificacao = dataModificacao;
		this.criadoPor = criadoPor;
		this.modificadoPor = modificadoPor;
	}

	


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public TypesUser getRole() {
		return role;
	}



	public void setRole(TypesUser role) {
		this.role = role;
	}



	public LocalDate getDataCriacao() {
		return dataCriacao;
	}



	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	public LocalDate getDataModificacao() {
		return dataModificacao;
	}



	public void setDataModificacao(LocalDate dataModificacao) {
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

}
