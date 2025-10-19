package org.example.meliorderservicespringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents the Order entity in the online store.
 * This class maps directly to the 'orders' table in the database and holds
 * the essential header information for a customer transaction.
 */

@Data //Getters and Setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order {

    /**
     * Unique identifier for the order (Primary Key).
     * Generated automatically by the database.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idorder;

    /**
     * Identifier of the customer who placed the order.
     * Links the order to a specific customer in the system.
     */

    private Long customerId;

    /**
     * Date and time when the order was created.
     * Defaults to the current time upon object instantiation.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * The total monetary amount of the order, including items, shipping, and taxes.
     */
    private BigDecimal totalAmount;

    /**
     * Current status of the order (e.g., PENDING, SHIPPED, DELIVERED).
     * Defaults to "PENDING".
     */

    private String status = "Pending";
}
