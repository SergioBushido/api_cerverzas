package com.example.practica_api_cerveza.repositories;

import com.example.practica_api_cerveza.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository <Beer, UUID> {
}
