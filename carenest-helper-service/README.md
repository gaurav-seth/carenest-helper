# carenest-helper-service

A starter Spring Boot + Docker + PostgreSQL service that provides a clean project skeleton for building production-grade microservices.  
This template allows developers to skip repetitive configuration steps and start writing business logic immediately.

This service includes a simple Helper Registration API to demonstrate:

- REST controller structure  
- Request/response handling  
- Validation  
- Database interaction  
- Docker deployment  

---

## Purpose of This Project

The goal of this project is to provide a ready-to-use backend foundation for CareNest or any microservice-based backend application.

Instead of spending time setting up packages, config, Docker, DB, controller patterns, and folder structure — you can directly plug in your business logic and start building features.

---

## Features

- Pre-configured Spring Boot project skeleton  
- REST endpoints (GET + POST examples)  
- Dockerized backend service  
- PostgreSQL running in Docker  
- Basic helper registration flow  
- Input validation using Jakarta Validation  
- Clean multi-layer architecture  
- Ready for expansion into a larger microservice ecosystem  

---

## Technology Stack

| Layer | Technology |
|------|------------|
| Backend | Spring Boot 3.x |
| Language | Java |
| Build Tool | Maven |
| Database | PostgreSQL |
| Deployment | Docker |
| Validation | Jakarta Validation |
| Testing | JUnit (optional placeholder) |

---

## Project Structure

carenest-helper-service/
├── src/main/java/com/carenest/helper/
│ ├── controller/
│ │ └── HelperController.java
│ ├── dto/
│ │ └── RegisterRequest.java
│ ├── service/
│ │ └── HelperRegistrationService.java
│ ├── entity/
│ │ └── Helper.java (optional future)
│ ├── repository/
│ │ └── HelperRepository.java (optional future)
│ └── HelperApplication.java
├── src/main/resources/
│ └── application.properties
├── Dockerfile
├── docker-compose.yml (optional)
└── README.md


---

## API Endpoints

### 1. Health Check / Demo GET Endpoint
**GET** `/api/helpers/hello`

**Response:**
Hello, World!



---

### 2. Register Helper
**POST** `/api/helpers/register`  
Consumes: `application/json`  
Produces: `text/plain`

#### Example Request Body
```json
{
  "name": "John Doe",
  "phoneNumber": "+919876543210"
}

Successful Response (200):
Registration started. OTP sent to +919876543210


Failure Response (400):
Registration failed for +919876543210


Registration Flow
Client → POST /register
       → Validate input (DTO)
       → Call HelperRegistrationService
       → Trigger OTP sending logic (stub)
       → Save data in PostgreSQL (future expansion)
       → Return success/failure response


Running With Docker
1. Build the JAR
mvn clean package -DskipTests

2. Build Docker Image
docker build -t carenest-helper-service .

3. Run the Container
docker run -p 8080:8080 carenest-helper-service

Running PostgreSQL with Docker
Run Postgres
docker run --name helper-db \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=helperdb \
  -p 5432:5432 \
  -d postgres

Update application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/helperdb
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

Run Locally (Without Docker)
mvn spring-boot:run


The service starts at:
http://localhost:8080/api/helpers/hello