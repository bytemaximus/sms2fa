package com.bytemaximus.sms2fa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "twiliosms")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class TwilioSMS {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonProperty("phone")
    @Column(nullable = false)
    private String phone;
    @JsonProperty("code")
    @Column
    private String code;
    @JsonProperty("channel")
    @Column
    private String channel;
    @JsonProperty("status")
    @Column
    private String status;
    @JsonProperty("valid")
    @Column
    private boolean valid;
    @JsonProperty("attempts")
    @Column
    private int attempts;
}
