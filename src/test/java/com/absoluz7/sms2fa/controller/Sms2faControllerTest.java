package com.absoluz7.sms2fa.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class Sms2faControllerTest {

    @Test
    public void authenticateTest() {
        Sms2faController controller = new Sms2faController();
        Map<String, String> payload = new HashMap<>();
        payload.put("apiKey", "testApiKey");
        String result = controller.authenticate(payload);
        assertEquals("Success", result);
    }

    @Test
    public void sendMessageTest() {
        Sms2faController controller = new Sms2faController();
        Map<String, String> payload = new HashMap<>();
        payload.put("phone", "1234567890");
        payload.put("message", "Test message");
        String result = controller.sendMessage(payload);
        assertEquals("Success", result);
    }
}
