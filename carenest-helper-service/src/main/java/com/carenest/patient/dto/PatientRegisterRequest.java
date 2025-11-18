package com.carenest.patient.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO representing a patient's registration details.
 * Captures minimal required fields such as name, phone number, and location.
 */
public class PatientRegisterRequest {

    @NotBlank(message = "Patient name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Location is required")
    private String location;

    /** 
     * Gets the patient's name.
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets the patient's name.
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the patient's phone number.
     * @return the phone number
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Sets the patient's phone number.
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Gets the patient's location.
     * @return the location
     */
    public String getLocation() { return location; }

    /**
     * Sets the patient's location.
     * @param location the location to set
     */
    public void setLocation(String location) { this.location = location; }
}
