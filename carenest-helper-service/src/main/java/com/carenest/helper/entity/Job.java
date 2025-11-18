package com.carenest.helper.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a job created by a patient.
 * Stored until a helper accepts the job.
 */
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String patientPhoneNumber;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Will store helper phone once someone accepts */
    @Column
    private String acceptedByHelper;

    /** True once any helper confirms */
    @Column(nullable = false)
    private boolean accepted = false;

    public UUID getId() { return id; }

    public String getPatientPhoneNumber() { return patientPhoneNumber; }
    public void setPatientPhoneNumber(String patientPhoneNumber) { this.patientPhoneNumber = patientPhoneNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }

    public String getAcceptedByHelper() { return acceptedByHelper; }
    public void setAcceptedByHelper(String acceptedByHelper) { this.acceptedByHelper = acceptedByHelper; }
}
