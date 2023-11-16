package com.example.demoparkapi.dtos;

public class UserResponseDto {
	private Long id;
	private String username;
	private String role;
	
	public UserResponseDto() {

		super();
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
