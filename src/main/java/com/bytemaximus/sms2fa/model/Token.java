package com.bytemaximus.sms2fa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "id")
public class Token {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonIgnore
    @ManyToOne(targetEntity = Credential.class)
    private Credential credential;
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
