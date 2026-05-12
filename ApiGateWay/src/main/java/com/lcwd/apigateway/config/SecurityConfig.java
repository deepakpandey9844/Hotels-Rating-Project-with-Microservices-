package com.lcwd.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity // Enable WebFlux Security as the API Gateway supports Spring WebFlux
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
            .authorizeExchange()
            .anyExchange().authenticated() // Enforce authentication for all requests
            .and()
            .oauth2Client() // Support for OAuth2 clients
            .and()
            .oauth2ResourceServer()
            .jwt(); // Configure JWT as the token mechanism for the resource server

        return httpSecurity.build();
    }
}
