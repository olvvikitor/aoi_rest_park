package com.example.demoparkapi.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// @EnableJpaAuditing habilita a auditoria JPA no aplicativo.
@EnableJpaAuditing
// @Configuration indica que esta classe é uma classe de configuração Spring.
@Configuration
public class SpringJpaAuditoriaconfig implements AuditorAware<String> {

    // @Override é uma anotação usada para indicar que o método está sobrescrevendo um método da interface pai ou da classe pai.
    @Override
    // AuditorAware é uma interface do Spring Data JPA usada para obter o nome do auditor atual (quem fez a última modificação).
    public Optional<String> getCurrentAuditor() {
        // Obtém o contexto de segurança do Spring para acessar informações de autenticação.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Verifica se há uma autenticação válida e o usuário está autenticado.
        if (authentication != null && authentication.isAuthenticated()) {
            // Retorna o nome do usuário autenticado como o auditor atual.
            return Optional.of(authentication.getName());
        }
        
        // Se não houver autenticação válida, retorna Optional vazio.
        return null;
    }
}
