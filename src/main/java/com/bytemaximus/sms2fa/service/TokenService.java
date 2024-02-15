package com.bytemaximus.sms2fa.service;

import com.bytemaximus.sms2fa.model.Credential;
import com.bytemaximus.sms2fa.model.Token;
import com.bytemaximus.sms2fa.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
@Log4j2
public class TokenService {

    private TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token createNewToken(Credential credential) {
        String clientSecret = "eyJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9";
        SecretKey sharedSecret = Keys.hmacShaKeyFor(clientSecret.getBytes());
        Instant now = Instant.now();
        Date expiration = Date.from(now.plus(5L, ChronoUnit.MINUTES));

        String jwt = Jwts.builder()
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(5L, ChronoUnit.MINUTES)))
                .issuer(credential.getApiKey())
                .subject(credential.getApiKey())
                .id(UUID.randomUUID().toString())
                .signWith(sharedSecret)
                .compact();

        log.debug("JWT generated successfully: {}", jwt);

        Token token = Token.builder()
                .credential(credential)
                .jwt(jwt)
                .expireAt(expiration)
                .responseLog("Generated Successfully")
                .build();

        tokenRepository.save(token);
        log.debug("Token saved to database.");

        return token;
    }
}
