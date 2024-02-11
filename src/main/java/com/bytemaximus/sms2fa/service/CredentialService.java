package com.bytemaximus.sms2fa.service;

import com.bytemaximus.sms2fa.model.Credential;
import com.bytemaximus.sms2fa.repository.CredentialRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public void newCredential(String apiKey, String apiSecret) {
        credentialRepository.save(
                Credential.builder()
                        .apiKey(apiKey)
                        .apiSecret(apiSecret)
                        .tokens(Collections.emptySet())
                        .build());
    }
}
