package com.carenest.helper.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carenest.helper.entity.Job;

/**
 * Repository for job persistence & assignment checks.
 */
public interface JobRepository extends JpaRepository<Job, UUID> {
	List<Job> findByAcceptedFalse();
}
