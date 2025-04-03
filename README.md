🐾 Animal Image Microservice
A lightweight Spring Boot application to fetch, store, and serve random images of animals (cat, dog, bear, duck) using public APIs. Images and their metadata are stored in an embedded H2 database. A simple HTML UI is included for manual testing.

📦 Features
    • RESTful API to fetch & retrieve animal images
    • Embedded H2 database (no setup required)
    • Stores image URL, type, timestamp, and binary data (BLOB)
    • Simple HTML UI with download link
    • Fallback to duck image if image not found
    • Logging with SLF4J (info, warn, error levels)
    • Automated integration tests using JUnit + MockMvc
    
🚀 Technologies Used
Layer	Technology
Language	Java 22
Framework	Spring Boot 3.4.4
API	Spring Web (REST Controller)
Database	Spring Data JPA + H2
UI	HTML + JavaScript (vanilla)
Logging	SLF4J with Logback
Testing	JUnit 5 + Spring MockMvc
Build Tool	Maven

📋 REST Endpoints
Method	Endpoint	Description
POST	/api/animals/fetch?type=bear&count=3	Fetch and store images
GET	/api/animals/last?type=bear	Get last stored image metadata
GET	/api/animals/last/image?type=bear	Get last stored image (as JPEG)

🧭 Architecture Overview
The architecture diagram below illustrates the overall structure and data flow of the application:

🧪 Running the App
1. Clone and Build
git clone https://github.com/your-username/animal-image-service.git
cd animal-image-service
mvn clean install
2. Run the App
mvn spring-boot:run
Important: Access the app at: http://localhost:8081/index.html

🖼️ UI Overview
    • Select animal type and number of images
    • Click Fetch & Save to store images
    • Click Load Last Image to view the latest stored image
    • If no image found, a duck 🦆 is shown instead
    
✅ Example Flow: Save & Fetch Image
    1. User clicks Fetch & Save
    2. Triggers: POST /api/animals/fetch?type=dog&count=1 (if fetch problem, error message displayed)
    3. Controller → Service → Repository → H2 DB
    4. User clicks Load Last Image
    5. Triggers: GET /api/animals/last/image?type=dog (if no image for animal type then an error message displayed and a duck image returns instead)
    6. Controller returns image
    7. UI displays image 
    
🧪 Run Tests
mvn test
👨‍💻 Author & License
Author: Hakan Taskin
Version: 1.0
Date: 2025-04-03
MIT License
