package com.example.practica_api_verveza.controller;


import com.example.practica_api_verveza.model.Customer;
import com.example.practica_api_verveza.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("{customerId}")
    public ResponseEntity updatePatchById(@PathVariable UUID customerId, @RequestBody Customer customer){

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteById(@PathVariable UUID customerId) {

        customerService.deleteById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PutMapping({"{customerId}"})
    public ResponseEntity updatedCustomer(@PathVariable UUID customerId, @RequestBody Customer customer) {

       customerService.updatedCustomerId(customerId, customer);

       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping
    public ResponseEntity handlePost(@RequestBody Customer customer) {
        Customer savedCustumer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location"," /api/v1/customer/"+savedCustumer.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {
        return customerService.listCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
      return   customerService.getCostumerById(customerId);
    }
}
