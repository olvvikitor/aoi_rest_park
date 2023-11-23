package com.example.demoparkapi.jwt;




import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtils {

	public static final String JWT_BEARER = "Bearer ";
	public static final String JWT_AUTHORIZATION = "Authorization";
	public static final String SECRET_KEY = "0123456789-0987654321-0123456789";
	public static final long EXPIRE_DAYS = 0;
	public static final long EXPIRE_HOURS = 0;
	public static final long EXPIRE_MINUTES = 120;

	  private static Logger log = LoggerFactory.getLogger(JwtUtils.class);
	
	
	private JwtUtils() {

	}

	private static SecretKey genereteKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
	
	private static Date toExpiration(Date start) {
		LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
		return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());	
		}

	public static JwtToken createToken(String username, String role) {
	try {
		Date issuedAt = new Date();
		Date limit = toExpiration(issuedAt);
		String token = Jwts.builder()
		.header().add("typ", "JWT")
		.and()
		.subject(username)
		.issuedAt(issuedAt)
		.expiration(limit)
		.signWith(genereteKey())
		.claim("role", role)
		.compact();
		log.info("username e role recebidos "+username,role + "method createToken");
		return new JwtToken(token);
	}catch (JwtException e) {
		log.info("Token criado");
	}
		return null;
	}
	
	private static Claims getClaimsFromToken(String token) {
		
			return Jwts.parser()
					.verifyWith(genereteKey())
					.build()
					.parseSignedClaims(refactorToken(token)).getPayload();
	
	}
	
	public static String getUsernameFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	
	public static boolean isTokenValid(String token) {
		try {
			Jwts.parser()
			.verifyWith(genereteKey())
			.build()
			.parseSignedClaims(refactorToken(token));
			return true;
		}catch (JwtException e) {
			log.error(String.format("token invalido {%s}", e.getMessage() ));
		}
		return false;
		
	}

	private static CharSequence refactorToken(String token) {
		if(token.contains(JWT_BEARER)) {
			return token.substring(JWT_BEARER.length());
		}
		return token;
	}


}
