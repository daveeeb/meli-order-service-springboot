package org.example.meliorderservicespringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.meliorderservicespringboot.models.Order;
import org.example.meliorderservicespringboot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for managing all external interactions related to the Order entity.
 * It exposes the CRUD operations via HTTP endpoints.
 */

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    /**
     * Dependency injection of OrderService to access business logic.
     * Declared as final and injected via the Lombok @RequiredArgsConstructor.
     */

    private final OrderService orderservice;

    /**
     * Retrieves a list of all orders.
     *
     * @return A ResponseEntity containing a list of Order objects and HTTP status 200 (OK).
     */

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = orderservice.findAll();
        return ResponseEntity.ok(list);
    }

    /**
     * Creates a new order.
     *
     * @param order The Order object received in the request body.
     * @return A ResponseEntity containing the created Order and HTTP status 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderservice.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    /**
     * Retrieves a single order by its unique ID.
     *
     * @param id The ID of the order, taken from the URL path.
     * @return A ResponseEntity with the Order and 200 (OK), or 404 (NOT_FOUND).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderservice.findById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Updates an existing order.
     *
     * @param id The ID of the order to be updated.
     * @param orderDetails The new details for the order.
     * @return A ResponseEntity with the updated Order and 200 (OK), or 404 (NOT_FOUND).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order updatedOrder = orderservice.update(id, orderDetails);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes an order by its unique ID.
     *
     * @param id The ID of the order to delete.
     * @return A ResponseEntity with HTTP status 204 (NO_CONTENT).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
