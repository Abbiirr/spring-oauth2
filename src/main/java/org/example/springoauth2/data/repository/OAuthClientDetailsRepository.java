package org.example.springoauth2.data.repository;

import org.example.springoauth2.data.entity.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, Long> {
    OAuthClientDetails findByRegistrationId(String registrationId);
}

