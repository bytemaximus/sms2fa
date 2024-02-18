package com.bytemaximus.sms2fa.data;

import com.bytemaximus.sms2fa.model.Credential;
import com.bytemaximus.sms2fa.model.Token;
import com.bytemaximus.sms2fa.repository.CredentialRepository;
import com.bytemaximus.sms2fa.repository.TokenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CredentialRepository credentialRepository;
    private final TokenRepository tokenRepository;

    public DataInitializer(CredentialRepository credentialRepository, TokenRepository tokenRepository) {
        this.credentialRepository = credentialRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void run(String... args) {
        generateCredentials();
        generateTokens();
    }

    public void generateCredentials() {
        Credential credential1 = Credential.builder()
                .apiKey("d0871480-5bd0-4722-92c0-9e217ef989f9")
                .apiSecret("ZDA4NzE0ODAtNWJkMC00NzIyLTkyYzAtOWUyMTdlZjk4OWY5")
                .tokens(Collections.emptySet())
                .build();

        Credential credential2 = Credential.builder()
                .apiKey("3f1262ea-d856-4b3f-97ac-da26aebab1ce")
                .apiSecret("M2YxMjYyZWEtZDg1Ni00YjNmLTk3YWMtZGEyNmFlYmFiMWNl")
                .tokens(Collections.emptySet())
                .build();

        credentialRepository.save(credential1);
        credentialRepository.save(credential2);
    }

    public void generateTokens() {
        List<Credential> credentials = credentialRepository.findAll();


        Token token1 = Token.builder()
                .jwt("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
                .expireAt(new Date())
                .credential(credentials.get(0))
                .responseLog("Token generated successfully.")
                .build();

        Token token2 = Token.builder()
                .jwt("this.is.a.bad.jwt.example")
                .expireAt(new Date())
                .credential(credentials.get(1))
                .responseLog("Error generating token - unable to parse date.")
                .build();

        tokenRepository.save(token1);
        tokenRepository.save(token2);
    }
}
