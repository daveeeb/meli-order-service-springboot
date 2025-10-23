# CHALLENGUE 5: Spring and Spring Boot in Java for Web Applications

## MELI Order Management Service — Sprint 1, 2 & 3

The **MELI Order Management Service** is a backend system built with **Spring Boot 3.0** and **Java 17**, designed to manage the full lifecycle of customer orders for an e-commerce platform.  
It provides a structured **RESTful API** that enables the creation, retrieval, updating, and deletion (CRUD) of orders, along with customer data association and validation.

The project is structured to support **multi-environment configuration** (`dev`, `test`, `prod`) using **Spring Profiles**, allowing seamless integration with both in-memory (H2) and production-grade (PostgreSQL) databases.  
It also integrates **OpenAPI (Swagger)** for API documentation, offering a user-friendly interface for testing and understanding endpoints.

Comprehensive **unit and integration testing** has been implemented with **JUnit 5** and **Mockito** to ensure the reliability of business logic and REST endpoints.  
This ensures that the API meets software quality standards and can be deployed confidently in real-world environments.

**Key Features:**
- Modular Spring Boot architecture following layered design (Controller → Service → Repository).
- Full CRUD operations for orders linked to customer entities.
- Environment-specific configurations and database connections.
- Swagger-based live API documentation.
- Automated testing suite for service and controller layers.
- Ready-to-deploy JAR build process with Maven.

---

## Features Overview

| Sprint | Focus | Key Deliverables |
| :---: | :--- | :--- |
| **Sprint 1** | Basic CRUD implementation | Order entity, REST controller, H2 database, and Postman collection. |
| **Sprint 2** | Environment configuration | Spring profiles (`dev`, `test`, `prod`), YAML config files, and system variables for database connections. |
| **Sprint 3** | API Documentation and Testing | **Swagger/OpenAPI** documentation integrated with Spring Boot, **JUnit 5 unit and integration tests** for the Order service and controller, and **test coverage for CRUD operations** and edge cases. Repository updated with Swagger UI and test scripts. |

---

## Tech Stack

- **Java 17**
- **Spring Boot 3.0**
- **Spring Data JPA**
- **H2 (Development & Test)**
- **PostgreSQL (Production)**
- **Maven 3.x**
- **Lombok**
- **Spring Profiles**
- **Swagger UI**

---
## REST API Endpoints

All endpoints are prefixed with `/api/v1/orders`.

| Method | Path | Description |
| :---: | :---: | :--- |
| `POST` | `/api/orders` | **C**reate: Creates a new order. |
| `GET` | `/api/orders/{id}` | **R**ead: Retrieves an order by ID. |
| `GET` | `/api/orders` | **R**ead: Retrieves all orders. |
| `PUT` | `/api/orders/{id}` | **U**pdate: Updates an existing order. |
| `DELETE` | `/api/orders/{id}`| **D**elete: Deletes an order. |

### Postman Collection Structure

| Request | Method | URL | Body (JSON) | Description | Expected Response |
| :---: | :---: | :--- | :--- | :--- | :--- |
| **CREATE Order** | `POST` | `http://localhost:8080/api/v1/orders` | `{"customerId": 101, "totalAmount": 150.75}` | Creates a new order for customer 101. | Status `201 Created`. `Order` object with an `id` and `createdAt`. |
| **READ All Orders** | `GET` | `http://localhost:8080/api/v1/orders` | (None) | Retrieves the list of all created orders. | Status `200 OK`. JSON array of `Order` objects. |
| **READ Order by ID** | `GET` | `http://localhost:8080/api/v1/orders/1` | (None) | Retrieves the order with ID 1. | Status `200 OK`. `Order` object. |
| **UPDATE Order** | `PUT` | `http://localhost:8080/api/v1/orders/1` | `{"totalAmount": 160.00, "status": "SHIPPED", "customerId": 101}` | Updates the amount and status of order 1. | Status `200 OK`. Updated `Order` object. |
| **DELETE Order** | `DELETE`| `http://localhost:8080/api/v1/orders/1` | (None) | Deletes the order with ID 1. | Status `204 No Content`. |
| **READ Not Found** | `GET` | `http://localhost:8080/api/v1/orders/999` | (None) | Attempts to retrieve a non-existent order. | Status `404 Not Found`. |

## Installation and Setup Guide
Step 1: Clone the Repository
```bash
git clone https://github.com/your-username/meli-order-service-springboot.git
cd meli-order-service-springboot
```

Step 2: Configure Environment Profiles
The project supports three main Spring Boot profiles:

| Profile | File | Database | Usage |
| :--- | :--- | :--- | :--- |
| `dev` | `application-dev.yml` | H2 (in-memory) | Local development. |
| `test` | `application-test.yml` | H2 (file-based) | Automated tests. |
| `prod` | `application-prod.yml` | PostgreSQL | Production deployment. |

Step 3: Set Environment Variables (for Production)
The production profile uses environment variables to manage database credentials securely.

| Variable | Description | Example |
| :--- | :--- | :--- |
| `DB_USER` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | `MySecurePass123` |
| `DB_NAME` | Database name | `meliordersdb` |
| `DB_HOST` | Database host | `localhost` |

## Example YAML configuration
```
spring:
  datasource:
    url: jdbc:postgresql://${DB_HOST:localhost}:5432/${DB_NAME:meliordersdb}
    username: ${DB_USER:postgres}
    password: ${DB_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
```
Step 4: Build the Application
Use Maven to compile and package the application.
```
./mvnw clean package -DskipTests
```

Step 5: Run the Application
- Development Mode (H2 Database)
```
java -jar target/meliorderservice.jar --spring.profiles.active=dev
```
- Test Mode (H2 File-based)
```
java -jar target/meliorderservice.jar --spring.profiles.active=test
```
- Production Mode (PostgreSQL)
```
java -jar target/meliorderservice.jar --spring.profiles.active=prod --DB_USER=postgres --DB_PASSWORD=MySecurePass123
```
Alternative: Using Spring Boot Command
You can also run the application directly with Maven:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
You can change dev for pro(production) or test(testing)

## Sprint 3 — Detailed Overview

### API Documentation using OpenAPI (Swagger)

**Goal:** Document the API endpoints with Swagger to provide an interactive and clear interface for developers and testers.

#### Configuration Steps

1. Add Swagger dependencies in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
```
2. Create a configuration class:
```java
package org.example.meliorderservicespringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Meli Order Service API")
                        .version("1.0.0")
                        .description("API documentation for the Meli Order Service project"));
    }
}

```
3. Run your application and open (View Swagger Documentation):
```
http://localhost:8080/swagger-ui/index.html
```


## Project Structure
```
meli-order-service-springboot/
│
├── src/
│   ├── main/
│   |    ├── java/org/example/meliorderservicespringboot/
|   |    |   |   ├── config/
│   |    │   │   └── SwaggerConfig.java
│   |    │   ├── controller/
│   |    │   │   ├── OrderController.java
│   |    │   │   └── CustomerController.java
│   |    │   ├── models/
│   |    │   │   ├── Order.java
│   |    │   │   └── Customer.java
│   |    │   ├── repository/
│   |    │   │   ├── OrderRepository.java
│   |    │   │   └── CustomerRepository.java
│   |    │   ├── service/
│   |    │   │   ├── OrderService.java
│   |    │   │   ├── CustomerService.java
│   |    │   │   └── implementation/
│   |    │   │       ├── OrderServiceImplementation.java
│   |    │   │       └── CustomerServiceImplementation.java
│   |    │   └── MeliOrderServiceSpringbootApplication.java
│   |    └── resources/
│   |        ├── application.yml
│   |        ├── application-dev.yml
│   |        ├── application-test.yml
│   |        └── application-prod.yml
|   | 
|    └── test/java/org/example/meliorderservicespringboot/
|        ├── controller/
|        │   └── OrderControllerTest.java
|        └── service/implementation/
|            └── OrderServiceImplementationTest.java
│
├── pom.xml
├── README.md
└── target/
    └── meliorderservice.jar
```

Javier David Barraza Ureña
ID NAO: 3303
Challengue 5: Spring and Spring Boot in Java for Web Applications
