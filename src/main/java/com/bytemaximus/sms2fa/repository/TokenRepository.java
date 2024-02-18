package com.bytemaximus.sms2fa.repository;

import com.bytemaximus.sms2fa.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM token t WHERE t.credential.id = ?1")
    List<Token> findAllByCredentialId(long credentialId);
}
