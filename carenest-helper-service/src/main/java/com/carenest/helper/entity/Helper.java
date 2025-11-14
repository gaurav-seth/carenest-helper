package com.carenest.helper.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Entity representing a helper in the system.
 * Stores personal details, phone verification status, OTP information, and audit timestamps.
 */
@Entity
@Table(name = "helpers")
public class Helper {

    /**
     * Unique identifier for the helper.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Name of the helper.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Phone number of the helper. Must be unique.
     */
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    /**
     * Address of the helper.
     */
    @Column(nullable = false)
    private String address;

    /**
     * Date of birth of the helper.
     */
    @Column(nullable = false)
    private LocalDate dob;

    /**
     * Indicates if the helper's phone number has been verified.
     */
    @Column(name = "phone_verified", nullable = false)
    private boolean phoneVerified = false;

    /**
     * Hash of the OTP sent to the helper.
     */
    @Column(name = "otp_hash")
    private String otpHash;

    /**
     * Expiry time of the OTP.
     */
    @Column(name = "otp_expires_at")
    private LocalDateTime otpExpiresAt;

    /**
     * Timestamp when the helper was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Timestamp when the helper was last updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * Gets the unique identifier of the helper.
     * @return the helper's UUID
     */
    public UUID getId() { return id; }

    /**
     * Gets the name of the helper.
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets the name of the helper.
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the phone number of the helper.
     * @return the phone number
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Sets the phone number of the helper.
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Gets the address of the helper.
     * @return the address
     */
    public String getAddress() { return address; }

    /**
     * Sets the address of the helper.
     * @param address the address to set
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Gets the date of birth of the helper.
     * @return the date of birth
     */
    public LocalDate getDob() { return dob; }

    /**
     * Sets the date of birth of the helper.
     * @param dob the date of birth to set
     */
    public void setDob(LocalDate dob) { this.dob = dob; }

    /**
     * Checks if the helper's phone number is verified.
     * @return true if verified, false otherwise
     */
    public boolean isPhoneVerified() { return phoneVerified; }

    /**
     * Sets the phone verification status.
     * @param phoneVerified the verification status to set
     */
    public void setPhoneVerified(boolean phoneVerified) { this.phoneVerified = phoneVerified; }

    /**
     * Gets the OTP hash.
     * @return the OTP hash
     */
    public String getOtpHash() { return otpHash; }

    /**
     * Sets the OTP hash.
     * @param otpHash the OTP hash to set
     */
    public void setOtpHash(String otpHash) { this.otpHash = otpHash; }

    /**
     * Gets the OTP expiry time.
     * @return the OTP expiry time
     */
    public LocalDateTime getOtpExpiresAt() { return otpExpiresAt; }

    /**
     * Sets the OTP expiry time.
     * @param otpExpiresAt the expiry time to set
     */
    public void setOtpExpiresAt(LocalDateTime otpExpiresAt) { this.otpExpiresAt = otpExpiresAt; }

    /**
     * Gets the creation timestamp.
     * @return the creation time
     */
    public LocalDateTime getCreatedAt() { return createdAt; }

    /**
     * Sets the creation timestamp.
     * @param createdAt the creation time to set
     */
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    /**
     * Gets the last updated timestamp.
     * @return the last updated time
     */
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    /**
     * Sets the last updated timestamp.
     * @param updatedAt the last updated time to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

