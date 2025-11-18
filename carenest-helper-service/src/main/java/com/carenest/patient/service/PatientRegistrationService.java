package com.carenest.patient.service;

import java.util.List;

import com.carenest.helper.dto.CreateJobRequest;
import com.carenest.helper.dto.JobCreatedMessage;
import com.carenest.patient.dto.PatientRegisterRequest;
import com.carenest.patient.entity.Patient;

/**
 * Service interface defining patient registration operations.
 */
public interface PatientRegistrationService {

	/**
	 * Registers a new patient in the system.
	 * @param request patient registration details
	 * @return true if registration succeeded
	 */
	boolean registerPatient(PatientRegisterRequest request);

	/**
	 * Retrieves list of all registered patients.
	 * @return list of Patient entities
	 */
	List<Patient> getAllPatients();
	
	/**
     * Creates a job on behalf of a patient and publishes
     * a job-created event to RabbitMQ so helpers can receive it.
     *
     * @param request the job creation request containing patient phone and location
     * @return the job-created message that was published
     */
    JobCreatedMessage createJobForPatient(CreateJobRequest request);

}
