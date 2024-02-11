package com.bytemaximus.sms2fa.repository;

import com.bytemaximus.sms2fa.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
