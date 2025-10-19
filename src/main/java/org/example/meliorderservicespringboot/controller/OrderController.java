package org.example.meliorderservicespringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.meliorderservicespringboot.models.Order;
import org.example.meliorderservicespringboot.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderservice;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = orderservice.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderservice.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
