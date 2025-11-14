
package com.carenest.helper.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

/**
 * DTO for capturing helper registration details.
 * Includes validation annotations for required fields and formats.
 */
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @Past(message = "DOB must be in the past")
    private LocalDate dob;

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

}
