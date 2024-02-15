package com.bytemaximus.sms2fa.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTest {

    @Test
    public void builderTest() {
        Date date = new Date();
        Token token = Token.builder()
                .credential(new Credential(1, "key", "secret", Collections.emptySet()))
                .jwt("testJwt")
                .expireAt(date)
                .responseLog("testResponseLog")
                .build();

        assertEquals(0, token.getId());
        assertEquals("secret", token.getCredential().getApiSecret());
        assertEquals("testJwt", token.getJwt());
        assertEquals(date, token.getExpireAt());
        assertEquals("testResponseLog", token.getResponseLog());
    }
}
