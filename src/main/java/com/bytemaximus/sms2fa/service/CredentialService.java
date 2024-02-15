package com.bytemaximus.sms2fa.service;

import com.bytemaximus.sms2fa.model.Credential;
import com.bytemaximus.sms2fa.model.Token;
import com.bytemaximus.sms2fa.repository.CredentialRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CredentialService {

    private final CredentialRepository credentialRepository;
    private TokenService tokenService;

    public CredentialService(CredentialRepository credentialRepository, TokenService tokenService) {
        this.credentialRepository = credentialRepository;
        this.tokenService = tokenService;
    }

    public String verifyCredential(String apiKey, String apiSecret) {
        Credential credential = credentialRepository.findCredentialByApiKeyAndSecret(apiKey, apiSecret);
        return credential != null ? createSuccess(credential) : createFailure();
    }

    private String createSuccess(Credential credential) {
        Token token = tokenService.createNewToken(credential);
        return new JSONObject()
                .put("responseMsg", "Success")
                .put("jwt", token.getJwt())
                .put("error", false)
                .toString();
    }

    private String createFailure() {
        return new JSONObject()
                .put("responseMsg", "Failure")
                .put("error", true)
                .toString();
    }
}
