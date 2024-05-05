package com.example.practica_api_verveza.services;

import com.example.practica_api_verveza.model.Beer;
import com.example.practica_api_verveza.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();
    Customer getCostumerById(UUID id);
    Customer saveNewCustomer(Customer customer);

    void updatedCustomerId(UUID custumerId, Customer customer);

    void deleteById(UUID customerId);
}
