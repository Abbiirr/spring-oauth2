package org.example.springoauth2.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_id", nullable = false)
    private String registrationId;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "redirect_uri", nullable = false)
    private String redirectUri;

    @Column(name = "authorization_grant_type", nullable = false)
    private String authorizationGrantType;

    @Column(name = "scope", nullable = false)
    private String scope;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "authorization_uri", nullable = false)
    private String authorizationUri;

    @Column(name = "token_uri", nullable = false)
    private String tokenUri;

    // Getters and Setters
}
