package com.carenest.patient.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing a patient in the system.
 * Stores basic identification and contact information, along with audit timestamps.
 */
@Entity
@Table(name = "patients")
public class Patient {

    /** Unique patient identifier. */
    @Id
    @GeneratedValue
    private UUID id;

    /** Name of the patient. */
    @Column(nullable = false)
    private String name;

    /** Phone number of the patient. */
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    /** Location of the patient (e.g., city/area). */
    @Column(nullable = false)
    private String location;

    /** Timestamp when the patient was created. */
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Timestamp when the patient was last updated. */
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public UUID getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
