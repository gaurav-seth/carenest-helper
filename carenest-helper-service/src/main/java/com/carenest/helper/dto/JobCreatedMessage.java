package com.carenest.helper.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Message payload broadcast to helpers when a new job is created.
 * Contains only minimum info needed for helper decision.
 */
public class JobCreatedMessage {

	private UUID jobId;
	private String patientPhoneNumber;
    private String location;
    private LocalDateTime createdAt;

    public UUID getJobId() { return jobId; }
    public void setJobId(UUID jobId) { this.jobId = jobId; }
    public String getPatientPhoneNumber() { return patientPhoneNumber; }
    public void setPatientPhoneNumber(String patientPhoneNumber) { this.patientPhoneNumber = patientPhoneNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
