package com.carenest.helper.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carenest.helper.dto.RegisterRequest;
import com.carenest.helper.entity.Job;
import com.carenest.helper.service.HelperRegistrationService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

/**
 * 
 * This class is a Spring REST controller for managing helper-related API endpoints. 
 * It is mapped to /api/helpers and provides the following functionality:
 * 		Initialization: The @PostConstruct method logs a message when the controller is 
 *      initialized.
		Hello Endpoint: The GET /hello endpoint returns a simple greeting.
		Registration Endpoint: The POST /register endpoint accepts a JSON payload 
		(RegisterRequest) with a phone number, validates it, and delegates registration 
		logic to HelperRegistrationService. 
		It returns a plain text response indicating success or failure.
 */

@RestController
@RequestMapping("/api/helpers")
public class HelperController {

	private final HelperRegistrationService helperRegistrationService;
	
	public HelperController(HelperRegistrationService verificationService) {
        this.helperRegistrationService = verificationService;
    }
	
	@PostConstruct
	/**
	 * Initializes the controller after dependency injection is complete.
	 * Logs a message indicating that the controller has been initialized.
	 */
	public void init() {
		System.out.println("HeperController initialized");
	}
	
	@GetMapping("/hello")
	/**
	 * Handles GET requests to /api/helpers/hello.
	 * @return A simple greeting string.
	 */
    public String sayHello() {
        return "Hello, World!";
    }

	/**
	 * Handles POST requests to /api/helpers/register.
	 * Accepts a JSON payload containing a phone number, validates it,
	 * and delegates registration logic to HelperRegistrationService.
	 * @param request The registration request containing the phone number.
	 * @return A plain text response indicating success or failure.
	 */
    @PostMapping(value = "/register", consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> registerHelper(@Valid @RequestBody RegisterRequest request) {
        boolean success = helperRegistrationService.registerHelper(request);
        if (success) {
            return ResponseEntity.ok("Registration started. OTP sent to " + request.getPhoneNumber());
        } else {
            return ResponseEntity.badRequest().body("Registration failed for " + request.getPhoneNumber());
        }
    }
    
    /**
     * Handles GET request to fetch all registered helpers.
     * @return a list of helper records from the system
     */
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> getAllHelpers() {
        return ResponseEntity.ok(helperRegistrationService.getAllHelpers());
    }
    
    /**
     * Allows a helper to accept a job.
     * FIRST helper who calls this successfully gets the job.
     *
     * @param jobId       ID of the job being accepted
     * @param helperPhone Phone number of helper accepting the job
     * @return Success response if job is assigned, error if already taken
     */
    @PostMapping("/accept-job/{jobId}")
    public ResponseEntity<String> acceptJob(
    		@PathVariable("jobId") UUID jobId,
    		@RequestParam("helperPhone") String helperPhone) {

        boolean success = helperRegistrationService.acceptJob(jobId, helperPhone);

        if (success) {
            return ResponseEntity.ok("🎉 Job assigned successfully to helper: " + helperPhone);
        } else {
            return ResponseEntity.badRequest().body("❌ Job already taken by another helper!");
        }
    }
    
    /**
     * Fetch all jobs that are still open (not accepted by any helper).
     *
     * @return List of available jobs
     */
    @GetMapping("/jobs/available")
    public ResponseEntity<List<Job>> getAvailableJobs() {
        List<Job> openJobs = helperRegistrationService.getOpenJobs();
        return ResponseEntity.ok(openJobs);
    }
}