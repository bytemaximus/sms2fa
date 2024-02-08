package com.absoluz7.sms2fa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Token {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonIgnore
    @Column(nullable = false)
    private String apiKey;
    @JsonIgnore
    @Column(nullable = false)
    private String apiSecret;
    @JsonProperty("jwt")
    @Column(nullable = false)
    private String jwt;
    @JsonProperty("exp")
    @Column(nullable = false)
    private String expireAt;
    @JsonProperty("log")
    @Column(nullable = false)
    private String responseLog;
}
