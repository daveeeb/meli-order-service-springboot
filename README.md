# Spring and Spring Boot in Java for Web Applications
## Order Management Service (Sprint 1)
 
This project implements the first sprint for the MELI order management system, using **Spring Boot 3.0** and **Java 17**.

A RESTful API is exposed for order management (CRUD), utilizing **Spring Data JPA** with **H2 Database** in-memory for the persistence layer during development.

---

## Prerequisites

* Java 17 or higher.
* Maven 3.x.
* Postman (for testing the API).

---

## Execution Instructions

There are two ways to start the service:

### Option 1: Using the Startup Script (Recommended)

The `startup.sh` script is responsible for compiling and running the application in a single step.

1.  Ensure the script has execution permissions:
    ```bash
    chmod +x startup.sh
    ```
2.  Execute the script from the project root:
    ```bash
    ./startup.sh
    ```
    The application will start at `http://localhost:8080`.

### Option 2: Using Maven Commands

1.  **Compile and Generate the JAR:**
    ```bash
    ./mvnw clean install -DskipTests
    ```
2.  **Execute the JAR:**
    ```bash
    java -jar target/order-app-0.0.1-SNAPSHOT.jar
    # (Adjust the JAR file name if different)
    ```

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

---

## 4. Postman Collection (Point 4)

Below is the structure of the requests you should include in your Postman Collection. To export the collection, create the requests in Postman, right-click on the collection name, and select **Export** as a **JSON** file.

### Postman Collection Structure

| Request | Method | URL | Body (JSON) | Description | Expected Response |
| :---: | :---: | :--- | :--- | :--- | :--- |
| **CREATE Order** | `POST` | `http://localhost:8080/api/orders` | `{"customerId": 101, "totalAmount": 150.75}` | Creates a new order for customer 101. | Status `201 Created`. `Order` object with an `id` and `createdAt`. |
| **READ All Orders** | `GET` | `http://localhost:8080/api/orders` | (None) | Retrieves the list of all created orders. | Status `200 OK`. JSON array of `Order` objects. |
| **READ Order by ID** | `GET` | `http://localhost:8080/api/orders/1` | (None) | Retrieves the order with ID 1. | Status `200 OK`. `Order` object. |
| **UPDATE Order** | `PUT` | `http://localhost:8080/api/orders/1` | `{"totalAmount": 160.00, "status": "SHIPPED", "customerId": 101}` | Updates the amount and status of order 1. | Status `200 OK`. Updated `Order` object. |
| **DELETE Order** | `DELETE`| `http://localhost:8080/api/orders/1` | (None) | Deletes the order with ID 1. | Status `204 No Content`. |
| **READ Not Found** | `GET` | `http://localhost:8080/api/orders/999` | (None) | Attempts to retrieve a non-existent order. | Status `404 Not Found`. |


Javier David Barraza Ureña ID NAO: 3303

