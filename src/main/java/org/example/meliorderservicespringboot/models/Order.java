package org.example.meliorderservicespringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data //Getters and Setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idorder;

    private Long customerId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private BigDecimal totalAmount;

    private String status = "Pending";
}
