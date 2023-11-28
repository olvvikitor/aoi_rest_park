package com.example.demoparkapi.config;



import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demoparkapi.jwt.JwtAuthenticationEntryPoint;
import com.example.demoparkapi.jwt.JwtAuthorizationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebMvc
public class SpringSecurityconfig  {
	private static final String[] DOCUMENTATION_OPENAPI = {
			"/docs/index.html",
			"/docs-park.html", "/docs-park/**",
			"/v3/api-docs/**",
			"/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
			"/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**"
	};

    // Configuração do filtro de segurança
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
        		.cors(x->x.disable())
                .csrf(t -> t.disable())  // Desativa CSRF
                .formLogin(t -> t.disable())  // Desativa login via formulário
                .httpBasic(t -> t.disable())  // Desativa autenticação básica HTTP
                .authorizeHttpRequests(t -> t
                		.requestMatchers(  antMatcher(HttpMethod.OPTIONS)).permitAll()  // Permite POST em /api/v1/users sem autenticação
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/users")).permitAll()  // Permite POST em /api/v1/users sem autenticação
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/auth")).permitAll()   // Permite POST em /api/v1/auth sem autenticação
                        .requestMatchers(DOCUMENTATION_OPENAPI).permitAll()   // Permite POST em /api/v1/auth sem autenticação
                        .anyRequest().authenticated())  // Todas as outras requisições requerem autenticação
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Define a política de criação de sessão como STATELESS
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)  // Adiciona o filtro de autorização JWT antes do filtro padrão de autenticação
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())) // Configura um ponto de entrada para lidar com erros de autenticação JWT
                .build();
    }

    // Bean para instanciar o filtro de autorização JWT
    @Bean
    JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    // Bean para configurar o encoder de senhas (utiliza BCrypt neste caso)
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para configurar o gerenciador de autenticação
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
     WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8081") // Adicione o seu frontend como um "allowed origin"
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);
            }
        };
}
    }
