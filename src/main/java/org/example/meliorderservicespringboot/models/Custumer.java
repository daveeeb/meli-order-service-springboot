package org.example.meliorderservicespringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "custumers")

/**
 * Represents a customer in the system.
 * Contains basic identification and contact information.
 */

public class Custumer {
     /**
     * Unique identifier for the customer (Primary Key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Customer’s first name.
     */
    private String firstName;

    /**
     * Customer’s last name.
     */
    private String lastName;

    /**
     * Customer’s email address (used for contact and account identification).
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Customer’s phone number.
     */
    private String phone;

    /**
     * Customer’s shipping address.
     */
    private String address;


}
