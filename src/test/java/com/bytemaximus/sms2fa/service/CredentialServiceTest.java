package com.bytemaximus.sms2fa.service;

import com.bytemaximus.sms2fa.repository.CredentialRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CredentialServiceTest {

    @Mock
    private CredentialRepository credentialRepository;
    @Mock
    private TokenService tokenService;

    @Test
    public void verifyCredentialTest() {
        CredentialService credentialService = new CredentialService(credentialRepository, tokenService);
        String apiKey = "testApiKey";
        String apiSecret = "testApiSecret";

        credentialService.verifyCredential(apiKey, apiSecret);

        verify(credentialRepository).findCredentialByApiKeyAndSecret(apiKey, apiSecret);
    }
}