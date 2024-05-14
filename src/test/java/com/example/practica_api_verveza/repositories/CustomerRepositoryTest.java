package com.example.practica_api_verveza.repositories;

import com.example.practica_api_verveza.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository CustomerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {
        Customer customer = customerRepository.save(Customer.builder()
                        .name("New Name")
                .build());
        assertThat(customer.getId()).isNotNull();
    }
}