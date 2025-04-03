/**
 * ================================================================================
 * File           : AnimalImageController.java
 * Author         : Hakan Taskin
 * Version        : 1.0
 * Revision       : 1
 * Created Date   : 2025-04-02
 * Last Updated   : 2025-04-03
 *
 * Description    :
 *   Spring REST Controller that exposes API endpoints for interacting with
 *   animal images. Supports fetching, storing, and retrieving the last image
 *   of a given animal type from the database.
 *
 * ================================================================================
 * Technologies Used:
 * ------------------
 * ☕ Java 22
 * 🌱 Spring Boot 3.4.4
 * 🔄 Spring Web (REST Controller)
 * 💬 @RestController, @RequestMapping, @PostMapping, @GetMapping
 *
 * Endpoints:
 * ----------
 * POST /api/animals/fetch?type={animal}&count={count}
 *   → Fetches random animal images and stores them
 *
 * GET /api/animals/last?type={animal}
 *   → Returns metadata of the last stored image for the given type
 *
 * GET /api/animals/last/image?type={animal}
 *   → Returns the raw image binary for display/download
 * ================================================================================
 */

// --- 7. Controller ---
package com.example.animalimages;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalImageController {

    // Spring REST controller
    // Declares a field to hold the AnimalImageService instance.
    private final AnimalImageService service;
    // This is where constructor injection happens.
    // Spring automatically provides (injects) an instance of AnimalImageService into the constructor
    public AnimalImageController(AnimalImageService service) {
        this.service = service;
    }

    @PostMapping("/fetch")
    public ResponseEntity<?> fetchAndSave(@RequestParam String type, @RequestParam int count) {
        List<AnimalImage> savedImages = service.fetchAndSaveImages(type, count);

        if (savedImages.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Info", "No images were fetched for the given type.");
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT); // 204 + custom header
        }
        return ResponseEntity.ok(savedImages); // 200 OK with body
    }

    @GetMapping("/last")
    public AnimalImage getLast(@RequestParam String type) {
        return service.getLastImage(type);
    }

    @GetMapping("/last/image")
    public ResponseEntity<byte[]> getLastImageData(@RequestParam String type) {
        AnimalImage image = service.getLastImage(type);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // or detect type
        return new ResponseEntity<>(image.getImageData(), headers, HttpStatus.OK);
    }
}
