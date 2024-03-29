package com.bytemaximus.sms2fa.controller;

import com.bytemaximus.sms2fa.service.CredentialService;
import com.bytemaximus.sms2fa.service.TwilioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
public class Controller2FA {

    private CredentialService credentialService;
    private TwilioService twilioService;

    public Controller2FA(CredentialService credentialService, TwilioService twilioService) {
        this.credentialService = credentialService;
        this.twilioService = twilioService;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/auth")
    public String authenticate(@RequestBody Map<String, String> payload) {
        String apiKey = payload.get("apiKey");
        String apiSecret = payload.get("apiSecret");

        return credentialService.verifyCredential(apiKey, apiSecret);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/sms/twilio")
    public String sendTwilioSMS(@RequestBody Map<String, String> payload) {
        String phone = payload.get("phone");

        log.debug("Received new Twilio sms request with phone number: {}", phone);

        twilioService.sendTwilioSMS(phone);

        return "Your request has been received.";
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/sms/twilio/verify")
    public String verifyTwilioSMS(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");
        String phone = payload.get("phone");

        log.debug("Received new Twilio verify request with code: {} and phone number: {}", code, phone);

        twilioService.verifyTwilioSMS(code, phone);

        return "Your request has been received.";
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/sms/custom")
    public String sendCustomSMS(@RequestBody Map<String, String> payload) {
        String apiKey = payload.get("apiKey");
        String phone = payload.get("phone");
        String message = payload.get("message");

        log.debug("Received new custom sms request with phone number: {} and message: {}", phone, message);

        return "Your request has been received.";
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/google/authenticator")
    public String sendGoogleAuth(@RequestBody Map<String, String> payload) {
        String apiKey = payload.get("apiKey");

        log.debug("Received new Google Authenticator request with API key: {}", apiKey);
        return "Success";
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/microsoft/authenticator")
    public String sendMicrosoftAuth(@RequestBody Map<String, String> payload) {
        String apiKey = payload.get("apiKey");

        log.debug("Received new Microsoft Authenticator request with API key: {}", apiKey);
        return "Success";
    }
}
