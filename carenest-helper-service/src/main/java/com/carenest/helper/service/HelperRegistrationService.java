package com.carenest.helper.service;

import com.carenest.helper.dto.RegisterRequest;

public interface HelperRegistrationService {
	
	/**
     * Registers the helper and triggers OTP generation.
     * Returns true if registration workflow started successfully.
     */
    boolean registerHelper(RegisterRequest request);
}
