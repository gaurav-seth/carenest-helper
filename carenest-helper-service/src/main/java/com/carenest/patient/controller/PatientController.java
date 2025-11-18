package com.carenest.patient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carenest.helper.dto.CreateJobRequest;
import com.carenest.helper.dto.JobCreatedMessage;
import com.carenest.patient.dto.PatientRegisterRequest;
import com.carenest.patient.service.PatientRegistrationService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

/**
 * Controller responsible for handling patient-related API operations.
 * 
 * Exposes endpoints for:
 * - Registering a new patient
 * - Health check endpoint
 * 
 * This class acts as the entry point for all patient-related workflows.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

	private final PatientRegistrationService patientRegistrationService;

	/**
	 * Constructor for injecting required patient service dependency.
	 * @param patientRegistrationService service that handles patient registration logic
	 */
	public PatientController(PatientRegistrationService patientRegistrationService) {
		this.patientRegistrationService = patientRegistrationService;
	}

	/**
	 * Initializes the controller after bean creation.
	 * Logs a simple initialization message.
	 */
	@PostConstruct
	public void init() {
		System.out.println("PatientController initialized");
	}

	/**
	 * Handles GET requests to /api/patients/hello.
	 * @return A simple greeting message.
	 */
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from PatientController!";
	}

	/**
	 * Handles POST requests to /api/patients/register.
	 * Accepts patient details and delegates registration logic to PatientRegistrationService.
	 *
	 * @param request the patient registration request containing name, phone number, and location
	 * @return ResponseEntity indicating success or failure of registration
	 */
	@PostMapping(value = "/register", consumes = "application/json", produces = "text/plain")
	public ResponseEntity<String> registerPatient(@Valid @RequestBody PatientRegisterRequest request) {
		boolean success = patientRegistrationService.registerPatient(request);

		if (success) {
			return ResponseEntity.ok("Patient registered successfully: " + request.getPhoneNumber());
		} else {
			return ResponseEntity.badRequest().body("Failed to register patient: " + request.getPhoneNumber());
		}
	}

	/**
	 * Handles GET request to fetch all registered patients.
	 * @return a list of all patient records stored in the system
	 */
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<?> getAllPatients() {
		return ResponseEntity.ok(patientRegistrationService.getAllPatients());
	}
	
	/**
     * Handles POST requests to /api/patients/create-job.
     * Creates a new job for the patient and publishes a job-created event
     * to RabbitMQ for helpers to consume.
     *
     * @param request job creation request containing patient phone and location
     * @return JobCreatedMessage representing the published job event
     */
    @PostMapping(value = "/create-job", consumes = "application/json", produces = "application/json")
    public ResponseEntity<JobCreatedMessage> createJob(@Valid @RequestBody CreateJobRequest request) {

        JobCreatedMessage message = patientRegistrationService.createJobForPatient(request);
        return ResponseEntity.ok(message);
    }

}
