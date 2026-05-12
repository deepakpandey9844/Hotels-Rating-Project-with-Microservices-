package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.config.intercepter.RestTemplateInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    // Define RestTemplate as a Spring Bean with LoadBalanced annotation
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(ClientRegistrationRepository clientRegistrationRepository,
                                      OAuth2AuthorizedClientRepository authorizedClientRepository) {

        RestTemplate restTemplate = new RestTemplate();

        // Create a list of interceptors for the RestTemplate
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        // Add the custom RestTemplateInterceptor
        interceptors.add(new RestTemplateInterceptor(authorizedClientManager(
                clientRegistrationRepository,
                authorizedClientRepository
        )));

        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    // Declare the OAuth2AuthorizedClientManager Bean
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        // Build the authorized client provider
        OAuth2AuthorizedClientProvider authorizedClientProvider = 
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        // Create and configure the DefaultOAuth2AuthorizedClientManager
        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}
