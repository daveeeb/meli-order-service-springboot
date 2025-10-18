package org.example.meliorderservicespringboot.service;

import org.example.meliorderservicespringboot.models.Order;
import java.util.List;

public interface OrderService {
    //CRUD
    Order save(Order order);
    List<org.example.meliorderservicespringboot.models.Order> findAll();
    Order findById(Long id);
    Order update(Long id, Order order);
    void delete(Long id);
}
