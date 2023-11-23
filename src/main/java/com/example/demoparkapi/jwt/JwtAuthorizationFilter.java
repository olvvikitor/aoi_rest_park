package com.example.demoparkapi.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
	private static Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	@Autowired
	private JwtUserDetailsService detailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);
		log.info("Token JWT recebido: {}", token);

		if (token == null || !token.startsWith(JwtUtils.JWT_BEARER)) {
		    log.info("JWT token está nulo, vazio ou não iniciado com 'Bearer'.");
		    filterChain.doFilter(request, response);
		    return;
		}

		if (!JwtUtils.isTokenValid(token)) {
		    log.warn("JWT token está inválido ou expirado");
		    filterChain.doFilter(request, response);
		    return;
		}

		String username = JwtUtils.getUsernameFromToken(token);
		log.info("Username extraído do token: {}", username);

		toAuthentication(request, username);
		filterChain.doFilter(request, response);
	}

	private void toAuthentication(HttpServletRequest request, String username) {
		UserDetails userDetails = detailService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
				.authenticated(userDetails, null, userDetails.getAuthorities());

		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

}
