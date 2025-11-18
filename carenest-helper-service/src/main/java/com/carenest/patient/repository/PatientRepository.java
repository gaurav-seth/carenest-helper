package com.carenest.patient.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carenest.patient.entity.Patient;

/**
 * Repository for performing CRUD operations on Patient entities.
 */
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * Finds a patient by phone number.
     * @param phoneNumber the phone number to search for
     * @return Optional containing patient if found
     */
    Optional<Patient> findByPhoneNumber(String phoneNumber);
}
