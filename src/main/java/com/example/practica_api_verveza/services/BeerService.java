package com.example.practica_api_verveza.services;


import com.example.practica_api_verveza.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    void updateBeerById(UUID beerId, BeerDTO beer);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDTO beer);
}