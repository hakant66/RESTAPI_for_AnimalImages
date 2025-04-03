/**
 * ================================================================================
 * File           : AnimalImageService.java
 * Author         : Hakan Taskin
 * Version        : 1.0
 * Revision       : 1
 * Created Date   : 2025-04-02
 * Last Updated   : 2025-04-03
 *
 * Description    :
 *   Service layer that contains business logic to fetch images from external
 *   public APIs based on animal type, store them in the H2 database, and
 *   retrieve the latest image stored for a given animal.
 *
 * ================================================================================
 * Technologies Used:
 * ------------------
 * ☕ Java 22
 * 🌱 Spring Boot 3.4.4
 * 🧠 Spring Service Layer
 * 🌐 Java URL + InputStream (to fetch remote images)
 * 💾 Spring Data JPA for persistence
 * 📜 SLF4J Logger for logging
 *
 * Notes:
 * ------
 * - Supported animal types: cat, dog, bear,duck
 * - Image fallback logic and retries handled in frontend
 * - Binary image data is stored as BLOB in the database
 * ================================================================================
 */
// --- 6. Service ---
package com.example.animalimages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AnimalImageService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalImageService.class);

    private final String dogUrl;
    private final String catUrl;
    private final String bearUrl;
    private final String duckUrl;

    private final AnimalImageRepository repository;

    public AnimalImageService(
            AnimalImageRepository repository,
            @Value("${animal.image.url.dog}") String dogUrl,
            @Value("${animal.image.url.cat}") String catUrl,
            @Value("${animal.image.url.bear}") String bearUrl,
            @Value("${animal.image.url.duck}") String duckUrl
    ) {
        this.repository = repository;
        this.dogUrl = dogUrl;
        this.catUrl = catUrl;
        this.bearUrl = bearUrl;
        this.duckUrl = duckUrl;
    }
    // ... use dogUrl, catUrl, bearUrl in your switch block

    public List<AnimalImage> fetchAndSaveImages(String animalType, int count) {
        List<AnimalImage> savedImages = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int width = 200 + random.nextInt(100);
            int height = 200 + random.nextInt(100);

            String imageUrl = switch (animalType.toLowerCase()) {
                case "dog" -> dogUrl + width + "/" + height;
                case "cat" -> catUrl + width + "/" + height;
                case "bear" -> bearUrl + width + "/" + height;
                //case "duck" -> duckUrl + width + "/" + height;
                case "duck" -> duckUrl;
                default -> throw new IllegalArgumentException("Unsupported animal type: " + animalType);
            };

            try {
                URL url = new URL(imageUrl);
                InputStream inputStream = url.openStream();
                byte[] imageBytes = inputStream.readAllBytes();
                inputStream.close();

                AnimalImage img = new AnimalImage(animalType.toLowerCase(), imageUrl, LocalDateTime.now(), imageBytes);
                repository.save(img);
                savedImages.add(img);

                logger.info("Saved image from URL: {} ({} bytes)", imageUrl, imageBytes.length);

            } catch (Exception e) {
                logger.error("Failed to fetch image from URL: {}", imageUrl, e);
                continue;
                // Optionally continue loop instead of stopping
                //throw new RuntimeException("Failed to download image", e);
            }
        }
        return savedImages;
    }
// Debug purposes. Remove or comment if not debugging
// Print the image URL to the console
// System.out.println("Fetching image from URL: " + imageUrl);

    public AnimalImage getLastImage(String animalType) {
        return repository.findTopByAnimalTypeOrderByTimestampDesc(animalType.toLowerCase())
                .orElseThrow(() -> new ImageNotFoundException("No image found for type: " + animalType));
    }
}
