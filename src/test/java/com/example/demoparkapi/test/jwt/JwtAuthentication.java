package com.example.demoparkapi.test.jwt;

import java.util.function.Consumer;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoparkapi.dtos.UserLoginDTO;
import com.example.demoparkapi.jwt.JwtToken;

public class JwtAuthentication {

    // Método para obter o cabeçalho de autorização com um token Bearer
    public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String username, String password) {

        // Realiza uma solicitação POST para o ponto de extremidade de autenticação para obter um token JWT
        String token = client
                .post()
                .uri("/api/v1/auth")
                .bodyValue(new UserLoginDTO(username, password))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody().getToken();

        // Retorna um Consumer que adiciona o cabeçalho de autorização com o token Bearer obtido
        return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
