package com.bytemaximus.sms2fa.data;

import com.bytemaximus.sms2fa.model.Credential;
import com.bytemaximus.sms2fa.repository.CredentialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CredentialRepository credentialRepository;

    public DataInitializer(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public void generate() {
        Credential credential1 = Credential.builder()
                .apiKey("smsApiKey")
                .apiSecret("smsApiSecret")
                .tokens(Collections.emptySet())
                .build();

        Credential credential2 = Credential.builder()
                .apiKey("googleApiKey")
                .apiSecret("googleApiSecret")
                .tokens(Collections.emptySet())
                .build();

        credentialRepository.save(credential1);
        credentialRepository.save(credential2);
    }

    @Override
    public void run(String... args) {
        generate();
    }
}
