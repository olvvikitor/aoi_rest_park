package com.example.demoparkapi.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demoparkapi.entities.User;
import com.example.demoparkapi.services.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	private UserService userService;
	
	
	public JwtUserDetailsService(UserService userService) {
		super();
		this.userService = userService;
	}

	

	public JwtUserDetailsService() {
		super();
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByName(username);
		return new JwtUserDatails(user);
	}
	
	public JwtToken getTokenAuthenticated(String username) {
		String role = userService.findRoleByUsername(username);
		return JwtUtils.createToken(username, role.substring("ROLE_".length()));
	}
	

}
