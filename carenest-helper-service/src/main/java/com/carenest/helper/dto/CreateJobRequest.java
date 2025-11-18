package com.carenest.helper.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO representing a job creation request from a patient.
 */
public class CreateJobRequest {

    @NotBlank(message = "Patient phone number is required")
    private String patientPhoneNumber;

    @NotBlank(message = "Location is required")
    private String location;

    public String getPatientPhoneNumber() { return patientPhoneNumber; }
    public void setPatientPhoneNumber(String patientPhoneNumber) { this.patientPhoneNumber = patientPhoneNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
