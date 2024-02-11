package com.bytemaximus.sms2fa.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialTest {

    @Test
    public void builderTest() {
        Credential credential = Credential.builder()
                .apiKey("testApiKey")
                .apiSecret("testApiSecret")
                .tokens(Collections.emptySet())
                .build();

        assertEquals(0, credential.getId());
        assertEquals("testApiKey", credential.getApiKey());
        assertEquals("testApiSecret", credential.getApiSecret());
        assertEquals(Collections.emptySet(), credential.getTokens());
    }
}
