package com.lcwd.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Enables Spring Security for the application
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allows method-level security annotations like @PreAuthorize
public class SecurityConfig {

    /**
     * Configures the application's security filter chain.
     * 
     * - All HTTP requests require authentication.
     * - Configures the application as an OAuth2 Resource Server with JWT support.
     *
     * @param security the {@link HttpSecurity} object for configuring security settings.
     * @return the configured {@link SecurityFilterChain}.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        // Configure security to enforce authentication for all incoming requests
        security
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated() // All requests must be authenticated
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt() // Enable JWT token validation for the OAuth2 resource server
            );

        // Build and return the SecurityFilterChain object
        return security.build();
    }
}
