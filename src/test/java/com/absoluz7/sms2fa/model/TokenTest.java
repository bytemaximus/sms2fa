package com.absoluz7.sms2fa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {

    @Test
    public void builderTest() {
        Token token = Token.builder()
                .id(1)
                .apiKey("testApiKey")
                .apiSecret("testApiSecret")
                .jwt("testJwt")
                .expireAt("testExpireAt")
                .responseLog("testResponseLog")
                .build();

        assertEquals(1, token.getId());
        assertEquals("testApiKey", token.getApiKey());
        assertEquals("testApiSecret", token.getApiSecret());
        assertEquals("testJwt", token.getJwt());
        assertEquals("testExpireAt", token.getExpireAt());
        assertEquals("testResponseLog", token.getResponseLog());
    }
}
