/**
 * ================================================================================
 * File           : AnimalImage.java
 * Author         : Hakan Taskin
 * Version        : 1.0
 * Revision       : 1
 * Created Date   : 2025-04-03
 * Last Updated   : 2025-04-03
 *
 * Description    :
 *   JPA Entity class representing an image of an animal. Each image is associated
 *   with a specific animal type (e.g., cat, dog, bear, duck), a source image URL,
 *   a timestamp indicating when it was fetched, and the binary image data itself.
 *
 * ================================================================================
 * Technologies Used:
 * ------------------
 * ☕ Java 22
 * 🌱 Spring Boot 3.4.4
 * 📦 Jakarta Persistence (JPA - Eclipse license)
 * 🧠 @Entity, @Id, @GeneratedValue, @Lob
 *
 * Key Fields:
 * -----------
 * Long id                → Primary key (auto-generated)
 * String animalType      → Animal type (cat, dog, bear, etc.)
 * String imageUrl        → URL of the fetched image
 * LocalDateTime timestamp→ When the image was fetched
 * byte[] imageData       → Raw binary image stored as a BLOB
 *
 * Notes:
 * ------
 * - Used by AnimalImageRepository for DB operations.
 * - Serves as the data model returned by the REST API.
 * ================================================================================
 */
// --- 2. Entity ---
package com.example.animalimages;

import jakarta.persistence.*; /* to map Java classes directly to database tables */
import java.time.LocalDateTime;

@Entity // Marks this class as a JPA entity, representing a table in the database.
public final class AnimalImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key to be generated automatically by the database.
    private Long id; // Unique identifier for each animal image record.
    private String animalType; // The type of animal the image depicts (e.g., "cat", "dog").
    private String imageUrl; // The URL from which the animal image was fetched.
    private LocalDateTime timestamp;  // The date and time when the animal image was fetched.

    @Lob // Indicates that this field should be persisted as a large object.
    @Column(columnDefinition = "BLOB") // Specifies the database column type as BLOB (Binary Large Object) for storing binary data.
    private byte[] imageData; // The actual binary data of the animal image.

    /**
     * Default constructor required by JPA.
     */
    public AnimalImage() {}

    /**
     * Constructor to create an AnimalImage object with all its attributes.
     *
     * @param animalType The type of animal in the image.
     * @param imageUrl   The URL of the image.
     * @param timestamp  The timestamp when the image was fetched.
     * @param imageData  The binary data of the image.
     */
    public AnimalImage(String animalType, String imageUrl, LocalDateTime timestamp, byte[] imageData) {
        this.animalType = animalType;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.imageData = imageData;
    }

    /* getters and setters */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAnimalType() { return animalType; }
    public void setAnimalType(String animalType) { this.animalType = animalType; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public byte[] getImageData() { return imageData; }
    public void setImageData(byte[] imageData) { this.imageData = imageData; }
}
