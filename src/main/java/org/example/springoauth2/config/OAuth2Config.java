package org.example.springoauth2.config;


import lombok.RequiredArgsConstructor;
import org.example.springoauth2.data.entity.OAuthClientDetails;
import org.example.springoauth2.data.repository.OAuthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OAuth2Config {

    private final OAuthClientDetailsRepository repository;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = repository.findAll().stream()
                .map(this::convertToClientRegistration)
                .collect(Collectors.toList());
        return registrations.isEmpty()
                ? new InMemoryClientRegistrationRepository()
                : new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration convertToClientRegistration(OAuthClientDetails client) {
        return ClientRegistration.withRegistrationId(client.getRegistrationId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .redirectUri(client.getRedirectUri())
                .authorizationGrantType(new AuthorizationGrantType(client.getAuthorizationGrantType()))
                .scope(client.getScope().split(","))
                .authorizationUri(client.getAuthorizationUri()) // Set authorizationUri
                .tokenUri(client.getTokenUri()) // Set tokenUri
                .issuerUri(client.getProvider())
                .build();
    }
}