package com.example.practica_api_cerveza.services;


import com.example.practica_api_cerveza.model.BeerDTO;
import com.example.practica_api_cerveza.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;


 public interface BeerService {

 Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

 Optional<BeerDTO> getBeerById(UUID id);

 BeerDTO saveNewBeer(BeerDTO beer);

 Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

 Boolean deleteById(UUID beerId);

 Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
 }