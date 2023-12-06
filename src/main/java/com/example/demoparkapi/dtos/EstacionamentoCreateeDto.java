package com.example.demoparkapi.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public class EstacionamentoCreateeDto {
	
	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "A placa do veiculo deve seguir o padrão 'XXX-0000'")
	private String placa;
	@NotBlank
	private String marca;
	@NotBlank
	private String modelo;
	@NotBlank
	private String cor;
	@NotBlank
	@Size(min = 11, max = 11)
	@CPF
	private String clientCpf;
	
	public EstacionamentoCreateeDto() {
		super();
	}
	  public static Builder builder() {
	        return new EstacionamentoCreateeDto().new Builder();
	    }
	  
	public EstacionamentoCreateeDto(
			@NotBlank @Size(min = 8, max = 8) @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "A placa do veiculo deve seguir o padrão 'XXX-0000'") String placa,
			@NotBlank String marca, @NotBlank String modelo, @NotBlank String cor,
			@Size(min = 11, max = 11) @CPF String clientCpf) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.clientCpf = clientCpf;
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
	// Inner class para o builder
    public class Builder {

        private Builder() {
            // Construtor privado para impedir a criação direta da instância
        }

        public Builder placa(String placa) {
            EstacionamentoCreateeDto.this.placa = placa;
            return this;
        }

        public Builder marca(String marca) {
            EstacionamentoCreateeDto.this.marca = marca;
            return this;
        }

        public Builder modelo(String modelo) {
            EstacionamentoCreateeDto.this.modelo = modelo;
            return this;
        }

        public Builder cor(String cor) {
            EstacionamentoCreateeDto.this.cor = cor;
            return this;
        }

        public Builder clientCpf(String clientCpf) {
            EstacionamentoCreateeDto.this.clientCpf = clientCpf;
            return this;
        }

        // Método para construir a instância final
        public EstacionamentoCreateeDto build() {
            return EstacionamentoCreateeDto.this;
        }
    }
}
