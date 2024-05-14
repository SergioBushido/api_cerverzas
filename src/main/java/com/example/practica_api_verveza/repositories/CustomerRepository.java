package com.example.practica_api_verveza.repositories;

import com.example.practica_api_verveza.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
