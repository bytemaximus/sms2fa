package com.bytemaximus.sms2fa.repository;

import com.bytemaximus.sms2fa.model.TwilioSMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TwilioRepository extends JpaRepository<TwilioSMS, Long> {

    @Query("SELECT t FROM twiliosms t WHERE t.phone = ?1")
    TwilioSMS findByPhone(String phone);
}
