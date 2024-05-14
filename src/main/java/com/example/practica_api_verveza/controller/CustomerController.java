package com.example.practica_api_verveza.controller;

import com.example.practica_api_verveza.model.Customer;
import com.example.practica_api_verveza.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer Controller", description = "API for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Partially update a customer by ID")
    @PatchMapping("{customerId}")
    public ResponseEntity<Void> updatePatchById(
            @Parameter(description = "ID of the customer to update") @PathVariable UUID customerId,
            @Parameter(description = "Updated customer information") @RequestBody Customer customer) {
        customerService.patchCustomerId(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a customer by ID")
    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID of the customer to delete") @PathVariable UUID customerId) {
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a customer by ID")
    @PutMapping("{customerId}")
    public ResponseEntity<Void> updatedCustomer(
            @Parameter(description = "ID of the customer to update") @PathVariable UUID customerId,
            @Parameter(description = "Updated customer information") @RequestBody Customer customer) {
        customerService.updatedCustomerId(customerId, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create a new customer")
    @PostMapping
    public ResponseEntity<Void> handlePost(
            @Parameter(description = "Customer to create") @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of all customers")
    @GetMapping
    public List<Customer> listCustomers() {
        return customerService.listCustomers();
    }

    @Operation(summary = "Get a customer by ID")
    @GetMapping("{customerId}")
    public Customer getCustomerById(
            @Parameter(description = "ID of the customer to retrieve") @PathVariable("customerId") UUID customerId) {
        return customerService.getCostumerById(customerId);
    }
}
