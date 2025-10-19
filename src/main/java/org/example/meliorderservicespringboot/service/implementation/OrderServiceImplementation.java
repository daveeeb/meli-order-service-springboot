package org.example.meliorderservicespringboot.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.meliorderservicespringboot.models.Order;
import org.example.meliorderservicespringboot.repository.OrderRepository;
import org.example.meliorderservicespringboot.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order>findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order update(Long id, Order order) {
        return null;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
