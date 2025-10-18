package org.example.meliorderservicespringboot.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.meliorderservicespringboot.models.Order;
import org.example.meliorderservicespringboot.repository.OrderRepository;
import org.example.meliorderservicespringboot.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing the business logic of Orders.
 * Handles the communication between the Controller and the Repository layers.
 */

@Service
@RequiredArgsConstructor

public class OrderServiceImplementation implements OrderService {

    /**
     * Dependency injection of OrderRepository to handle data access.
     * Injected automatically via the Lombok @RequiredArgsConstructor.
     */

    private final OrderRepository orderRepository;

    /**
     * Saves a new order or updates an existing one in the database.
     *
     * @param order The Order object to be saved.
     * @return The persisted Order object with its generated ID.
     */
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Retrieves a list of all existing orders from the database.
     *
     * @return A List containing all Order objects.
     */
    @Override
    public List<Order>findAll() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its unique ID.
     *
     * @param id The ID of the order to find.
     * @return The found Order object, or null if no order exists with that ID.
     */

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing order.
     * NOTE: In a real scenario, this method would contain complex update logic
     * to prevent unauthorized field changes.
     *
     * @param id The ID of the order to update.
     * @param order The Order object containing the new details.
     * @return The updated Order object.
     */

    @Override
    public Order update(Long id, Order order) {
        return null;
    }

    /**
     * Deletes an order from the database by its ID.
     *
     * @param id The ID of the order to delete.
     */

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
