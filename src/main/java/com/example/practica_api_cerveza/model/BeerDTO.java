package com.example.practica_api_cerveza.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
public class BeerDTO {

    private UUID id;
    private Integer version;

    //si ponemos validaciones aqui hay que poner @validated en el controller
    @NotBlank//para no meter caracteres en blanco
    @NotNull
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @NotNull
    @NotBlank
    private String upc;
    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
