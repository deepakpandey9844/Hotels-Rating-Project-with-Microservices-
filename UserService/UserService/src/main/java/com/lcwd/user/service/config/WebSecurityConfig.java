package com.lcwd.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Enable method-level security
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Enforce authentication for all requests
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt() // Configure JWT-based authentication
            );

        return security.build();
    }
}
