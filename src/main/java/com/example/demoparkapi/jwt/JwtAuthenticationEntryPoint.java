package com.example.demoparkapi.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException { 
		log.info("http status 401 {}", authException.getMessage());
		response.setHeader("www-authenticate", "Bearer realm='/api/v1/auth' ");
		response.sendError(401);
		
	}

}
