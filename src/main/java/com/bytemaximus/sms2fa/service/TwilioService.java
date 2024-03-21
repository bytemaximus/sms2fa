package com.bytemaximus.sms2fa.service;

import com.bytemaximus.sms2fa.model.TwilioSMS;
import com.bytemaximus.sms2fa.repository.TwilioRepository;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TwilioService {

    private TwilioRepository twilioRepository;

    public TwilioService(TwilioRepository twilioRepository) {
        this.twilioRepository = twilioRepository;
    }

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;
    @Value("${twilio.account.token}")
    private String twilioAccountToken;
    @Value("${twilio.service.sid}")
    private String twilioServiceSid;
    @Value("${twilio.channel}")
    private String twilioChannel;

    public void sendTwilioSMS(String phone) {

        TwilioSMS sms = TwilioSMS.builder()
                .phone(phone)
                .build();

        Twilio.init(twilioAccountSid, twilioAccountToken);
        Verification verification = Verification.creator(
                twilioServiceSid,
                sms.getPhone(),
                twilioChannel)
                .create();

        sms.setChannel(twilioChannel);
        sms.setStatus(verification.getStatus());
        sms.setValid(verification.getValid());
        sms.setAttempts(verification.getSendCodeAttempts().size());

        twilioRepository.save(sms);

        log.info("Twilio SMS sent with status - {}", sms.getStatus());
    }

    public void verifyTwilioSMS(String code, String phone) {
        TwilioSMS sms = twilioRepository.findByPhone(phone);
        sms.setCode(code);

        if (StringUtils.isNotBlank(sms.getStatus()) && sms.getStatus().equalsIgnoreCase("pending")) {
            log.info("Verifying Twilio SMS");
            Twilio.init(twilioAccountSid, twilioAccountToken);
            VerificationCheck verificationCheck = VerificationCheck.creator(
                    twilioServiceSid)
                    .setTo(sms.getPhone())
                    .setCode(sms.getCode())
                    .create();

            sms.setStatus(verificationCheck.getStatus());
            sms.setValid(verificationCheck.getValid());
        }

        twilioRepository.save(sms);

        log.info("Twilio SMS verified with status - {}", sms.getStatus());
    }
}
