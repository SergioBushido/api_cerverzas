package com.example.practica_api_cerveza.mappers;

import com.example.practica_api_cerveza.entities.Customer;
import com.example.practica_api_cerveza.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}