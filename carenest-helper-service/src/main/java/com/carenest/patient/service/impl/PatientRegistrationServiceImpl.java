package com.carenest.patient.service.impl;

import static com.carenest.app.config.RabbitMQConfig.JOB_BROADCAST_EXCHANGE;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carenest.helper.dto.CreateJobRequest;
import com.carenest.helper.dto.JobCreatedMessage;
import com.carenest.helper.entity.Job;
import com.carenest.helper.repository.JobRepository;
import com.carenest.patient.dto.PatientRegisterRequest;
import com.carenest.patient.entity.Patient;
import com.carenest.patient.repository.PatientRepository;
import com.carenest.patient.service.PatientRegistrationService;

/**
 * Service implementation responsible for patient registration logic.
 * Handles persistence and updates timestamps for auditing.
 */
@Service
public class PatientRegistrationServiceImpl implements PatientRegistrationService {

	private final PatientRepository patientRepository;

	private final RabbitTemplate rabbitTemplate;
	
	private final JobRepository jobRepository;

	/**
	 * Constructs the service with the required repository.
	 * @param patientRepository repository for patient entities
	 */
	public PatientRegistrationServiceImpl(PatientRepository patientRepository,
			 JobRepository jobRepository, 
			RabbitTemplate rabbitTemplate) {
		this.patientRepository = patientRepository;
		 this.jobRepository = jobRepository;
		this.rabbitTemplate = rabbitTemplate;
	}

	/**
	 * Registers a patient by creating or updating their record in the database.
	 * @param request registration data containing basic patient information
	 * @return true if registration succeeded
	 */
	@Override
	@Transactional
	public boolean registerPatient(PatientRegisterRequest request) {

		Patient patient = patientRepository.findByPhoneNumber(request.getPhoneNumber())
				.orElseGet(Patient::new);

		patient.setName(request.getName());
		patient.setPhoneNumber(request.getPhoneNumber());
		patient.setLocation(request.getLocation());

		patient.setUpdatedAt(LocalDateTime.now());
		if (patient.getCreatedAt() == null) patient.setCreatedAt(LocalDateTime.now());

		patientRepository.save(patient);

		System.out.println("[Patient Service] Registration completed for: " + request.getPhoneNumber());
		return true;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}
	
	@Override
    public JobCreatedMessage createJobForPatient(CreateJobRequest request) {

        // 1. Find patient by phone
        Patient patient = patientRepository.findByPhoneNumber(request.getPatientPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with phone: " + request.getPatientPhoneNumber()));
        
        Job job = new Job();
        job.setPatientPhoneNumber(request.getPatientPhoneNumber());
        job.setLocation(request.getLocation());
        jobRepository.save(job);
                
        // 2. Build job message
        JobCreatedMessage message = new JobCreatedMessage();
        message.setJobId(UUID.randomUUID());
        message.setPatientPhoneNumber(patient.getPhoneNumber());
        message.setLocation(request.getLocation());
        message.setCreatedAt(LocalDateTime.now());

        // 3. Publish to RabbitMQ fanout exchange
        rabbitTemplate.convertAndSend(JOB_BROADCAST_EXCHANGE, "", message);

        System.out.println("[Patient Service] Published job-created event for patient: " 
                + patient.getPhoneNumber() + " with jobId: " + message.getJobId());

        return message;
    }
}
