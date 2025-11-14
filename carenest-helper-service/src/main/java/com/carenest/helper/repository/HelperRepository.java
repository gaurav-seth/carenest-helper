
package com.carenest.helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carenest.helper.entity.Helper;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing Helper entities from the database.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface HelperRepository extends JpaRepository<Helper, UUID> {

    /**
     * Finds a helper by their phone number.
     * @param phoneNumber the phone number to search for
     * @return an Optional containing the Helper if found, or empty if not found
     */
    Optional<Helper> findByPhoneNumber(String phoneNumber);
}
