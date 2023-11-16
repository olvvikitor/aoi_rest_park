package com.example.demoparkapi.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDatails extends User {
	
	
	private com.example.demoparkapi.entities.User user;

	public JwtUserDatails(com.example.demoparkapi.entities.User user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
		this.user = user;
		
	}
	
	public Long getId() {
		return user.getId();
	}
	
	public String getRole() {
		return this.user.getRole().name();
	}
}
