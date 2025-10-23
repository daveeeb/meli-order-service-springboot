package org.example.meliorderservicespringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.meliorderservicespringboot.models.Custumer;
import org.example.meliorderservicespringboot.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateOrder_Success() throws Exception {
        // Crear cliente simulado (Custumer)
        Custumer custumer = new Custumer();
        custumer.setId(1L);
        custumer.setFirstName("Juan");
        custumer.setLastName("Pérez");
        custumer.setEmail("juanperez@example.com");
        custumer.setPhone("555-123-4567");
        custumer.setAddress("Av. Reforma 100, CDMX");

        // Crear orden simulada
        Order order = new Order();
        order.setCustumer(custumer);
        order.setTotalAmount(new BigDecimal("499.99"));
        order.setPaymentMethod("CREDIT_CARD");
        order.setShippingAddress("Av. Reforma 100, CDMX");
        order.setStatus("Pending");
        order.setNotes("Entrega urgente");
        order.setCreatedAt(LocalDateTime.now());

        // Ejecutar petición POST a /api/orders
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.custumer.firstName").value("Juan"))
                .andExpect(jsonPath("$.totalAmount").value(499.99));
    }
}
