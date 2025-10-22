package org.example.meliorderservicespringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.meliorderservicespringboot.models.Custumer;
import org.example.meliorderservicespringboot.service.CustumerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("api/customers")
    @RequiredArgsConstructor
    public class CustomerController {

        private final CustumerService customerService;

        @GetMapping
        public ResponseEntity<List<Custumer>> getAllCustomers() {
            return ResponseEntity.ok(customerService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Custumer> getCustomerById(@PathVariable Long id) {
            Custumer customer = customerService.findById(id);
            return customer != null ? ResponseEntity.ok(customer) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PostMapping
        public ResponseEntity<Custumer> createCustomer(@RequestBody Custumer customer) {
            return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
