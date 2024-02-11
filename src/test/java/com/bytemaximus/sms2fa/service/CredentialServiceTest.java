package com.bytemaximus.sms2fa.service;

import com.bytemaximus.sms2fa.model.Credential;
import com.bytemaximus.sms2fa.repository.CredentialRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CredentialServiceTest {

    @Mock
    private CredentialRepository credentialRepository;

    @Test
    public void newCredentialTest() {
        CredentialService credentialService = new CredentialService(credentialRepository);
        String apiKey = "testApiKey";
        String apiSecret = "testApiSecret";

        credentialService.newCredential(apiKey, apiSecret);

        verify(credentialRepository).save(Credential.builder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .tokens(Collections.emptySet())
                .build());
    }
}