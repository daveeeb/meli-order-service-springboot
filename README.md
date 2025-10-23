# Spring and Spring Boot in Java for Web Applications

## MELI Order Management Service — Sprint 1 & 2

This project implements the first two sprints for the **MELI Order Management System**, built with **Spring Boot 3.0** and **Java 17**.  
It provides a **RESTful API** for order management (CRUD) and introduces configuration of **environment profiles** for development, testing, and production environments.

---

## Features Overview

| Sprint | Focus | Key Deliverables |
| :---: | :--- | :--- |
| **Sprint 1** | Basic CRUD implementation | Order entity, REST controller, H2 database, and Postman collection. |
| **Sprint 2** | Environment configuration | Spring profiles (`dev`, `test`, `prod`), YAML config files, and system variables for database connections. |

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

## Project Structure
```
meli-order-service-springboot/
│
├── src/
│   ├── main/
│   │   ├── java/org/example/meliorderservicespringboot/
│   │   │   ├── controller/
│   │   │   │   ├── OrderController.java
│   │   │   │   └── CustomerController.java
│   │   │   ├── models/
│   │   │   │   ├── Order.java
│   │   │   │   └── Customer.java
│   │   │   ├── repository/
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── CustomerRepository.java
│   │   │   ├── service/
│   │   │   │   ├── OrderService.java
│   │   │   │   ├── CustomerService.java
│   │   │   │   └── implementation/
│   │   │   │       ├── OrderServiceImplementation.java
│   │   │   │       └── CustomerServiceImplementation.java
│   │   │   └── MeliOrderServiceSpringbootApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-test.yml
│   │       └── application-prod.yml
│   └── test/
│       └── java/org/example/meliorderservicespringboot/
│           └── OrderControllerTest.java
│
├── pom.xml
├── README.md
└── target/
    └── meliorderservice.jar
```

Javier David Barraza Ureña
ID NAO: 3303
Challengue 5: Spring and Spring Boot in Java for Web Applications
