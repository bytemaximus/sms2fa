package com.bytemaximus.sms2fa.repository;

import com.bytemaximus.sms2fa.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
}
