package com.example.practica_api_cerveza.repositories;

import com.example.practica_api_cerveza.entities.Beer;
import com.example.practica_api_cerveza.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameToLong(){

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer2325425223254252232542522325425223254252232542522325425223254252232542522325425223254252Beer2325425223254252232542522325425223254252232542522325425223254252232542522325425223254252")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("23254252")
                    .price(new BigDecimal("11.99"))
                    .build());
            beerRepository.flush();

        });


    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("234234234234")
                .price(new BigDecimal("11.99"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

}