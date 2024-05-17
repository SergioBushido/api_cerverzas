package com.example.practica_api_cerveza.mappers;



import com.example.practica_api_cerveza.entities.Beer;
import com.example.practica_api_cerveza.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);

}