🐾 Animal Image Microservice
A lightweight Spring Boot application designed to fetch, store, and serve random images of animals (cat, dog, bear, duck) using public APIs. The images and their metadata are stored in an embedded H2 database.

✅ Basics
Project name: RESTAPI_for_AnimalImages
Maven builds a JAR: target/RESTAPI_for_AnimalImages-1.0-SNAPSHOT.jar
App runs on port 8081

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

A. Clone & Run from GitHub 

1. Clone the GitHub Repository 
git clone https://github.com/hakant66/RESTAPI_for_AnimalImages.git
cd RESTAPI_for_AnimalImages
mvn clean install
2. Make Sure Java Is Installed 
* You need Java 17 or Java 22 installed. Check with: 
java -version
3. Build the Application (Using Maven) 
* Make sure you have Maven installed. Check with: 
mvn -version
* If mvn exists, run:
mvn clean package
* This generates the executable JAR file in target 
4.a. Run the application (Using Maven) 
mvn spring-boot:run
4.b. Run the application (Using provided scripts) 
Windows:
scripts\run.bat
Linux:
chmod +x scripts/run.sh
./scripts/run.sh
4.c. Run the application (Manual run with Java ) 
java -Dserver.port=8081 -jar target/app.jar
5. Open the Web App 
http://localhost:8081/index.html
Important: App is using port 8081 

🖼️ UI Overview
    • http://localhost:8081/index.html
    • Select an animal (cat, dog, bear, duck) 
    • Choose how many images to fetch 
    • Click Fetch & Save to store images
    • Click Load Last Image to view the latest stored image
    • If no image found, a duck 🦆 is shown instead

✅ Example Flow: Save & Fetch Image
    1. User clicks Fetch & Save in UI
    2. POST /api/animals/fetch?type=dog&count=1 → hits Controller
    3. Controller passes to AnimalImageService
    4. Service:
        ◦ Builds image URL (e.g., https://place.dog/200/220)
        ◦ Downloads it as byte[]
        ◦ Creates and saves AnimalImage via Repository
    5. Repository stores it in H2 DB
    6. User clicks Load Last Image → GET /api/animals/last/image?type=dog
    7. Controller returns latest image bytes as image/jpeg
    8. UI displays the image 

🧪 Run Tests
mvn test
Note: test provides surefire-reports 

🧪 Running on Docker 
docker run --rm -p 8081:8081 -v ${PWD}/target:/app -w /app eclipse-temurin:22-jdk-alpine java -jar app.jar

📋 Common Troubleshooting
    • ❌ java: command not found → Java not installed or not in PATH
    • ❌ Could not find or load main class → Make sure you ran mvn package
    • ❌ 404 not found → Make sure backend is running, then reload the browser

👨‍💻 Author & License
Author: Hakan Taskin
Version: 1.0
Date: 2025-04-03
License: MIT License

