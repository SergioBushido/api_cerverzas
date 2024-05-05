package com.example.practica_api_verveza.services;

import com.example.practica_api_verveza.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updatedBeerId(UUID beerId, Beer beer);

    void deleteById(UUID beerId);
}
