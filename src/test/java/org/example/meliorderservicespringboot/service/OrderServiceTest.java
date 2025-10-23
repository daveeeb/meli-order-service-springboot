package org.example.meliorderservicespringboot.service;

import org.example.meliorderservicespringboot.models.Custumer;
import org.example.meliorderservicespringboot.models.Order;
import org.example.meliorderservicespringboot.repository.OrderRepository;
import org.example.meliorderservicespringboot.service.implementation.OrderServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplementationTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImplementation orderService;

    private Custumer custumer;
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        custumer = new Custumer();
        custumer.setId(1L);
        custumer.setFirstName("Ana");
        custumer.setLastName("Gómez");
        custumer.setEmail("ana.gomez@example.com");
        custumer.setPhone("555-123-4567");
        custumer.setAddress("Av. Reforma 100, CDMX");

        order = new Order();
        order.setIdorder(1);
        order.setCustumer(custumer);
        order.setTotalAmount(new BigDecimal("1200.00"));
        order.setPaymentMethod("CREDIT_CARD");
        order.setShippingAddress("Av. Reforma 100, CDMX");
        order.setStatus("Pending");
        order.setNotes("Entrega rápida");
    }

    @Test
    void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.save(order);

        assertNotNull(savedOrder);
        assertEquals("Pending", savedOrder.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testFindAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> result = orderService.findAll();

        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.findById(1L);

        assertNotNull(result);
        assertEquals(order.getIdorder(), result.getIdorder());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        Order result = orderService.findById(99L);

        assertNull(result);
        verify(orderRepository, times(1)).findById(99L);
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.delete(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }
}
