package com.example.practica_api_verveza.controller;

import com.example.practica_api_verveza.model.Beer;
import com.example.practica_api_verveza.services.BeerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
@Tag(name = "Beer Controller", description = "API for managing beers")
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = "/{beerId}";
    private final BeerService beerService;

    @Operation(summary = "Partially update a beer by ID")
    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updatePatchById(
            @Parameter(description = "ID of the beer to update") @PathVariable UUID beerId,
            @Parameter(description = "Updated beer information") @RequestBody Beer beer) {
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a beer by ID")
    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID of the beer to delete") @PathVariable UUID beerId) {
        beerService.deleteById(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a beer by ID")
    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<Void> updateBeer(
            @Parameter(description = "ID of the beer to update") @PathVariable UUID beerId,
            @Parameter(description = "Updated beer information") @RequestBody Beer beer) {
        beerService.updateBeerById(beerId, beer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create a new beer")
    @PostMapping
    public ResponseEntity<Void> handlePost(
            @Parameter(description = "Beer to create") @RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of all beers")
    @GetMapping
    public List<Beer> getAllBeers() {
        return beerService.listBeers();
    }

    @Operation(summary = "Get a beer by ID")
    @GetMapping(BEER_PATH_ID)
    public Beer getBeerById(
            @Parameter(description = "ID of the beer to retrieve") @PathVariable("beerId") UUID beerId) {
        log.debug("log desde controlador");
        return beerService.getBeerById(beerId);
    }
}
