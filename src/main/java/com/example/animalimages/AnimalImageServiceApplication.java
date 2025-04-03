/**
 * ===========================================================================
 * Project Name : Animal Image Microservice
 * File Name    : AnimalImageServiceApplication.java
 * Author       : Hakan Taskin
 * Version      : 1.0
 * Revision     : 1
 * Date         : 2025-04-03
 * Description  :
 *   Main entry point for a Spring Boot microservice that provides REST APIs
 *   for fetching, storing, and retrieving images of cats, dogs, bears and ducks.
 *   Images are stored in an embedded H2 database with binary image data
 *   (as BLOBs) and metadata. The app exposes APIs for clients to fetch and
 *   retrieve image resources and includes full exception handling and logging.
 * ================================================================================
 * Technologies Used:
 * ------------------
 * ☕ Java 22
 * 🌱 Spring Boot 3.4.4
 * 🔄 Spring Web (REST API)
 * 💾 Spring Data JPA + Embedded H2 Database
 * 🧪 JUnit 5 + Spring MockMvc (for testing)
 * 📦 Maven (build tool)
 * 🖼️ HTML + JavaScript (frontend UI)
 * 🧰 SLF4J with Logback (for logging)
 * 🐳 Docker / Docker Compose (optional for containerization)
 * ================================================================================
 * Key Features:
 *   - REST endpoints:
 *     - POST /api/animals/fetch?type=cat&count=3 → fetches & stores images
 *     - GET /api/animals/last?type=dog → retrieves last stored image
 *   - Supported animal types: cat, dog, bear, duck.
 *   - Binary image storage (as BLOB)
 *   - Fallback image logic (duck image) in frontend
 *   - Simple UI to test service manually
 *   - Fallback support & error handling. Logging via SLF4J LoggerFactory (info, warn, error)
 *   - H2 embedded DB for easy local setup (no external DB required)
 *   - Automated tests using JUnit & MockMvc
 *   - application.properties is used for server port, image locations, H2 DB, etc.
 * ===========================================================================
 */

// --- 1. Main Application ---
package com.example.animalimages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Entry point for the Animal Image Service Spring Boot application.
 */
@SpringBootApplication
public class AnimalImageServiceApplication {

    // Standard logger setup (simpler than using AtomicReference)
    private static final Logger logger = LoggerFactory.getLogger(AnimalImageServiceApplication.class);

    public static void main(String[] args) {
        // Set server port (can also be set in application.properties)
        // Below is for backup purpose, defined in application.properties
        // System.setProperty("server.port", "8081");

        // Print to console and log file
        System.out.println("Server running on port 8081...");
        logger.info("Server running on port 8081");

        // Start Spring Boot app
        SpringApplication.run(AnimalImageServiceApplication.class, args);
    }
}


