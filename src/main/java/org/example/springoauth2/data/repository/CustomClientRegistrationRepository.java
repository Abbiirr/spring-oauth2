package org.example.springoauth2.data.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@Primary
@RequiredArgsConstructor
public class CustomClientRegistrationRepository implements ClientRegistrationRepository {

    private final OAuthClientDetailsRepository repository;
    private final Map<String, ClientRegistration> registrationMap = new HashMap<>();


    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        return registrationMap.get(registrationId);
    }

    @PostConstruct
    private void loadClientRegistrations() {
        repository.findAll().forEach(client -> {
            ClientRegistration registration = ClientRegistrations.fromIssuerLocation(client.getProvider())
                    .registrationId(client.getRegistrationId())
                    .clientId(client.getClientId())
                    .clientSecret(client.getClientSecret())
                    .redirectUri(client.getRedirectUri())
                    .authorizationGrantType(new AuthorizationGrantType(client.getAuthorizationGrantType()))
                    .scope(client.getScope().split(","))
                    .build();
            registrationMap.put(client.getRegistrationId(), registration);
        });
    }
}

