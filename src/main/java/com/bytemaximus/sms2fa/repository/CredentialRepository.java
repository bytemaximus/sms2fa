package com.bytemaximus.sms2fa.repository;

import com.bytemaximus.sms2fa.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    @Query("SELECT c FROM credential c WHERE c.apiKey = ?1")
    Credential findCredentialByApiKey(String apiKey);

    @Query("SELECT c FROM credential c WHERE c.apiKey = ?1 AND c.apiSecret = ?2")
    Credential findCredentialByApiKeyAndSecret(String apiKey, String apiSecret);
}
