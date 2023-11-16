package com.example.demoparkapi.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import com.example.demoparkapi.jwt.JwtAuthorizationFilter;

@EnableMethodSecurity
@EnableWebMvc
@Configuration
public class SpringSecurityconfig {
	
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(t -> t.disable())
				.formLogin(t -> t.disable())
				.httpBasic(t -> t.disable())
				.authorizeHttpRequests(t-> t.requestMatchers(antMatcher(HttpMethod.POST,"/api/v1/users")).permitAll()
				.requestMatchers(antMatcher(HttpMethod.POST,"/api/v1/auth")).permitAll()
				     .anyRequest()
				     .authenticated())
				.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	@Bean
	JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}
	
	
	@Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	
	}

	@Bean
    AuthenticationManager authenticationMenager( AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
