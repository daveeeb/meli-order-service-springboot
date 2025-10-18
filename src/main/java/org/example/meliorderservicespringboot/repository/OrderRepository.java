package org.example.meliorderservicespringboot.repository;

import org.example.meliorderservicespringboot.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
