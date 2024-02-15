package com.bytemaximus.sms2fa.controller;

import com.bytemaximus.sms2fa.service.CredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class Controller2FATest {

    @Mock
    private CredentialService credentialService;
    private Controller2FA controller;

    @BeforeEach
    void setUp() {
        controller = new Controller2FA(credentialService);
    }
//
//    @Test
//    public void authenticateTest() {
//        Map<String, String> payload = new HashMap<>();
//        payload.put("apiKey", "testApiKey");
//        payload.put("apiSecret", "testApiSecret");
//
//        verify(credentialService).verifyCredential("testApiKey", "testApiSecret");
//    }

    @Test
    public void sendCustomSMSTest() {
        Map<String, String> payload = new HashMap<>();
        payload.put("apiKey", "Test api key");
        payload.put("phone", "1234567890");
        payload.put("message", "Test message");

        String result = controller.sendCustomSMS(payload);

        assertEquals("Success", result);
    }

    @Test
    public void sendTwilioSMSTest() {
        Map<String, String> payload = new HashMap<>();
        payload.put("phone", "1234567890");
        payload.put("message", "Test message");

        String result = controller.sendTwilioSMS(payload);

        assertEquals("Success", result);
    }
}
