package org.example.meliorderservicespringboot.repository;

import org.example.meliorderservicespringboot.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Order entity.
 * Extends JpaRepository to automatically inherit standard CRUD operations
 * (save, findById, findAll, deleteById) without manual implementation.
 */

public interface OrderRepository extends JpaRepository<Order,Long> {
    // Custom database queries or finders can be defined here if needed.
}
