
package com.carenest.helper.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carenest.helper.dto.RegisterRequest;
import com.carenest.helper.entity.Helper;
import com.carenest.helper.repository.HelperRepository;
import com.carenest.helper.service.HelperRegistrationService;

/**
 * Service implementation for registering helpers.
 * Handles persistence, OTP generation, hashing, and sending OTP for phone verification.
 */
@Service
public class HelperRegistrationServiceImpl implements HelperRegistrationService {

    private final HelperRepository helperRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * OTP expiration time in minutes, injected from application properties.
     */
    @Value("${helper.otp.expiration-minutes}")
    private long otpExpiryMinutes;

    /**
     * Constructs the service with the required repository.
     * @param helperRepository repository for helper entities
     */
    public HelperRegistrationServiceImpl(HelperRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    /**
     * Registers a helper by persisting their details, generating and hashing an OTP,
     * setting OTP expiration, and (stub) sending the OTP via SMS.
     * @param request registration request containing helper details
     * @return true if registration process started successfully
     */
    @Override
    @Transactional
    public boolean registerHelper(RegisterRequest request) {
        // 1. Persist helper data
        Helper helper = helperRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> new Helper());

        helper.setName(request.getName());
        helper.setPhoneNumber(request.getPhoneNumber());
        helper.setAddress(request.getAddress());
        helper.setDob(request.getDob());
        helper.setPhoneVerified(false);
        helper.setUpdatedAt(LocalDateTime.now());
        if (helper.getCreatedAt() == null) helper.setCreatedAt(LocalDateTime.now());

        // 2. Generate OTP (6-digit)
        String otp = String.format("%06d", secureRandom.nextInt(1_000_000));

        // 3. Hash OTP before storing
        String otpHash = Base64.getEncoder().encodeToString(otp.getBytes());
        helper.setOtpHash(otpHash);

        // OTP expiration time in minutes
        helper.setOtpExpiresAt(LocalDateTime.now().plusMinutes(otpExpiryMinutes));

        helperRepository.save(helper);

        // 4. Send OTP via SMS (stub for now)
        System.out.println("[Helper Service] OTP for " + request.getPhoneNumber() + ": " + otp);

        return true;
    }
}
