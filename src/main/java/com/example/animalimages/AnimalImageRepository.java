/**
 * ================================================================================
 * File           : AnimalImageRepository.java
 * Author         : Hakan Taskin
 * Version        : 1.0
 * Revision       : 1
 * Created Date   : 2025-04-03
 * Last Updated   : 2025-04-03
 *
 * Description    : This is an interface that acts as the data access layer for the AnimalImage entity
 *   Spring Data JPA repository interface for managing AnimalImage entities.
 *   Provides built-in CRUD operations and a custom method to retrieve
 *   the latest image stored for a specific animal type.
 *   No SQL or DAO needed — handled by Spring
 *
 * ================================================================================
 * Technologies Used:
 * ------------------
 * ☕ Java 22
 * 🌱 Spring Boot 3.4.4
 * 💾 Spring Data JPA
 * 🧠 Repository Pattern
 * 📜 Optional from java.util for null-safe results
 *
 * Key Methods:
 * ------------
 * findTopByAnimalTypeOrderByTimestampDesc(String animalType)
 *   → Returns the most recent AnimalImage record for the given type
 *
 * Inherits:
 * ---------
 * JpaRepository<AnimalImage, Long>
 *   → Provides standard methods like save(), findById(), findAll(), deleteById(), etc.
 * ================================================================================
 */
// --- 3. Repository ---
package com.example.animalimages;

import com.example.animalimages.AnimalImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* most recently saved image for the given animal type */
@Repository
public interface AnimalImageRepository extends JpaRepository<AnimalImage, Long> {
    Optional<AnimalImage> findTopByAnimalTypeOrderByTimestampDesc(String animalType);
}
