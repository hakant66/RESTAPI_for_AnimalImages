// --- 4. Custom Exception ---
// ImageNotFound exception handling
package com.example.animalimages;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
