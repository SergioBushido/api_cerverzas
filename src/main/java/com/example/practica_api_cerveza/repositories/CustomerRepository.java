package com.example.practica_api_cerveza.repositories;

import com.example.practica_api_cerveza.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
