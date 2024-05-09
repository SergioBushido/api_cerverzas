package com.example.practica_api_verveza.controller;


import com.example.practica_api_verveza.model.Beer;
import com.example.practica_api_verveza.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PatchMapping("{beerId}")
    public ResponseEntity updatePatchById(@PathVariable UUID beerId,@RequestBody Beer beer) {

        beerService.patchBeerId(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@PathVariable UUID beerId) {

        beerService.deleteById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping("{beerId}")
    public ResponseEntity updateBeer(@PathVariable UUID beerId,@RequestBody Beer beer) {

        beerService.updatedBeerId(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
        //otra manera
       // return ResponseEntity.noContent().build();


    }

    @PostMapping
    //Esto es lo mismo que PostMapping
    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity  handlePost(@RequestBody Beer beer) {
       Beer savedBeer = beerService.saveNewBeer(beer);

       //Para meter en el encabezado la localizacion y datos de la cerveza insertada
       HttpHeaders headers = new HttpHeaders();
       headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

       return new ResponseEntity(headers, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET)
   // @RequestMapping("/") lo mismo es

    public List<Beer> getAllBeers() {
        return beerService.listBeers();
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("log desde controlador");
        return beerService.getBeerById(beerId);
    }
}
