package com.carenest.helper.service;

import java.util.List;

import com.carenest.helper.dto.RegisterRequest;
import com.carenest.helper.entity.Helper;
import com.carenest.helper.entity.Job;

public interface HelperRegistrationService {
	
	/**
     * Registers the helper and triggers OTP generation.
     * Returns true if registration workflow started successfully.
     */
    boolean registerHelper(RegisterRequest request);
    
    /**
     * Retrieves list of all registered helpers.
     * @return list of Helper entities
     */
    List<Helper> getAllHelpers();
    
    /**
     * Allows a helper to claim a job.
     * @return true if helper successfully claimed it, false otherwise.
     */
    boolean acceptJob(java.util.UUID jobId, String helperPhone);
    
    /**
     * Returns all open jobs that are not yet accepted by any helper.
     */
    List<Job> getOpenJobs();
}
