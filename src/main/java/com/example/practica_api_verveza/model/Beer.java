package com.example.practica_api_verveza.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;



@Data
public class Beer {

    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    Beer(UUID id, Integer version, String beerName, BeerStyle beerStyle, String upc, Integer quantityOnHand, BigDecimal price, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.version = version;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
        this.price = price;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static BeerBuilder builder() {
        return new BeerBuilder();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Beer;
    }

    public static class BeerBuilder {
        private UUID id;
        private Integer version;
        private String beerName;
        private BeerStyle beerStyle;
        private String upc;
        private Integer quantityOnHand;
        private BigDecimal price;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        BeerBuilder() {
        }

        public BeerBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public BeerBuilder version(Integer version) {
            this.version = version;
            return this;
        }

        public BeerBuilder beerName(String beerName) {
            this.beerName = beerName;
            return this;
        }

        public BeerBuilder beerStyle(BeerStyle beerStyle) {
            this.beerStyle = beerStyle;
            return this;
        }

        public BeerBuilder upc(String upc) {
            this.upc = upc;
            return this;
        }

        public BeerBuilder quantityOnHand(Integer quantityOnHand) {
            this.quantityOnHand = quantityOnHand;
            return this;
        }

        public BeerBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BeerBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public BeerBuilder updateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Beer build() {
            return new Beer(this.id, this.version, this.beerName, this.beerStyle, this.upc, this.quantityOnHand, this.price, this.createDate, this.updateDate);
        }

        public String toString() {
            return "Beer.BeerBuilder(id=" + this.id + ", version=" + this.version + ", beerName=" + this.beerName + ", beerStyle=" + this.beerStyle + ", upc=" + this.upc + ", quantityOnHand=" + this.quantityOnHand + ", price=" + this.price + ", createDate=" + this.createDate + ", updateDate=" + this.updateDate + ")";
        }
    }
}
