package com.example.demoparkapi.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demoparkapi.entities.User;
import com.example.demoparkapi.services.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	 private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

	@Autowired
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
		log.info("user name recebido metofo loadByUserName {%s}" + username);
		return new JwtUserDatails(user);
		
	}
	
	public JwtToken getTokenAuthenticated(String username) {
		String role = userService.findRoleByUsername(username);
		log.info("role recebido metofo  getTokenAuthenticated {%s}" + username);

		return JwtUtils.createToken(username, role.substring("ROLE_".length()));
	}
	

}
