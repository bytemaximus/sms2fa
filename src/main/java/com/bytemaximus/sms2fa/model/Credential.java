package com.bytemaximus.sms2fa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "credential")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "id")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String apiKey;
    @Column(nullable = false)
    private String apiSecret;
    @OneToMany(mappedBy = "credential")
    private Set<Token> tokens;
}
