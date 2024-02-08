package com.absoluz7.sms2fa.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
public class Sms2faController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/auth")
    public String authenticate(@RequestBody Map<String, String> payload) {
        String apiKey = payload.get("apiKey");

        log.debug("Received new authentication request with API key: {}", apiKey);

        return "Success";
    }

    public String sendMessage(@RequestBody Map<String, String> payload) {
        String phone = payload.get("phone");
        String message = payload.get("message");

        log.debug("Received new message request with phone number: {} and message: {}", phone, message);

        return "Success";
    }
}
