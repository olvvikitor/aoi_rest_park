package com.example.demoparkapi.dtos;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDTO  {
	

	@NotBlank
	@Email(message = "formato de e-mail invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 13)
	private String password;
	
	public UserCreateDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	public UserCreateDTO() {
		super();
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

	
}
