package com.example.demoparkapi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demoparkapi.enuns.StatusVaga;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vagas")
@EntityListeners(AuditingEntityListener.class)
public class Vaga implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo", nullable = false, unique = true, length = 4)
	private String codigo;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusVaga status;
	
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

		public Vaga(Long id, String codigo, StatusVaga status, LocalDateTime dataCriacao, LocalDateTime dataModificacao,
				String criadoPor, String modificadoPor) {
			super();
			this.id = id;
			this.codigo = codigo;
			this.status = status;
			this.dataCriacao = dataCriacao;
			this.dataModificacao = dataModificacao;
			this.criadoPor = criadoPor;
			this.modificadoPor = modificadoPor;
		}

		public Vaga() {
			super();
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

		public StatusVaga getStatus() {
			return status;
		}

		public void setStatus(StatusVaga status) {
			this.status = status;
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
			return Objects.hash(codigo, id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vaga other = (Vaga) obj;
			return Objects.equals(codigo, other.codigo) && Objects.equals(id, other.id);
		}
		
}
