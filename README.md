🐾 Animal Image Microservice
A lightweight Spring Boot application designed to fetch, store, and serve random images of animals (cat, dog, bear, duck) using public APIs. The images and their metadata are stored in an embedded H2 database.

📦 Features
RESTful API to fetch & retrieve animal images
Embedded H2 database (no setup required)
Stores image URL, type, timestamp, and binary data (BLOB)
Simple HTML UI with download link
Fallback to duck image if image not found
Logging with SLF4J (info, warn, error levels)
Automated integration tests using JUnit + MockMvc

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
Clone and Build
bash
git clone https://github.com/your-username/animal-image-service.git
cd animal-image-service
mvn clean install
Run the App
bash
mvn spring-boot:run
Access the app at: http://localhost:8081/index.html

🖼️ UI Overview
Select animal type and number of images
Click Fetch & Save to store images
Click Load Last Image to view the latest stored image
If no image found, a duck 🦆 is shown instead

✅ Example Flow: Save & Fetch Image
User clicks Fetch & Save
Triggers: POST /api/animals/fetch?type=dog&count=1
If fetch problem, an error message is displayed
Controller → Service → Repository → H2 DB
User clicks Load Last Image
Triggers: GET /api/animals/last/image?type=dog
If no image for animal type, an error message is displayed and a duck image returns instead
Controller returns image
UI displays image

🧪 Run Tests
bash
mvn test

👨‍💻 Author & License
Author: Hakan Taskin
Version: 1.0
Date: 2025-04-03
License: MIT License

