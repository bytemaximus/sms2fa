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
public class Sms2faControllerTest {

    @Mock
    private CredentialService credentialService;
    private Sms2faController controller;

    @BeforeEach
    void setUp() {
        controller = new Sms2faController(credentialService);
    }

    @Test
    public void authenticateTest() {
        Map<String, String> payload = new HashMap<>();
        payload.put("apiKey", "testApiKey");
        payload.put("apiSecret", "testApiSecret");

        String result = controller.authenticate(payload);

        assertEquals("Success", result);
        verify(credentialService).newCredential("testApiKey", "testApiSecret");
    }

    @Test
    public void sendMessageTest() {
        Map<String, String> payload = new HashMap<>();
        payload.put("phone", "1234567890");
        payload.put("message", "Test message");

        String result = controller.sendMessage(payload);

        assertEquals("Success", result);
    }
}
