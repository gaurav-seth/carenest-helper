package com.carenest.helper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carenest.helper.dto.RegisterRequest;
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
}
