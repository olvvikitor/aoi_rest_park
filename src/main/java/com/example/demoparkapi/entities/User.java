package com.example.demoparkapi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demoparkapi.enuns.TypesUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//Esta anotação marca a classe como uma entidade JPA, o que significa que será mapeada para uma tabela de banco de dados.
@Entity
//Especifica o nome da tabela para a entidade.
@Table(name = "users")
//EntityListeners é usado para especificar classes de listener de callback.
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

	/**
	 * Serializable é uma interface de marcação que indica que a classe é serializável.
	 */
	private static final long serialVersionUID = 1L;

	// @Id especifica a chave primária da entidade.
	@Id
	// @GeneratedValue fornece a estratégia de geração para os valores da chave primária.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Column é usado para especificar os detalhes da coluna à qual um campo ou propriedade será mapeado.
	@Column(name = "username", nullable = false, length = 100, unique = true)
	private String username;

	@Column(name = "password", nullable = false, length = 200)
	private String password;

	// @Enumerated é usado para especificar o tipo de enumeração.
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 25)
	private TypesUser role = TypesUser.ROLE_CLIENT;

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

	public User() {
		super();
	}

	

	public User(Long id, String username, String password, TypesUser role, LocalDateTime dataCriacao,
			LocalDateTime dataModificacao, String criadoPor, String modificadoPor) {
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

}
