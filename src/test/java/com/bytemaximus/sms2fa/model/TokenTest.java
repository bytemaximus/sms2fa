package com.bytemaximus.sms2fa.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {

    @Test
    public void builderTest() {
        Token token = Token.builder()
                .credential(new Credential(1, "key", "secret", Collections.emptySet()))
                .jwt("testJwt")
                .expireAt("testExpireAt")
                .responseLog("testResponseLog")
                .build();

        assertEquals(0, token.getId());
        assertEquals("secret", token.getCredential().getApiSecret());
        assertEquals("testJwt", token.getJwt());
        assertEquals("testExpireAt", token.getExpireAt());
        assertEquals("testResponseLog", token.getResponseLog());
    }
}
