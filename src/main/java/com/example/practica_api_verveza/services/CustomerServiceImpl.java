package com.example.practica_api_verveza.services;

import com.example.practica_api_verveza.model.Beer;
import com.example.practica_api_verveza.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl(){
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Sergio")
                .version("blak")
                .createDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Sergio")
                .version("blak")
                .createDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Sergio")
                .version("blak")
                .createDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(),customer1);
        customerMap.put(customer2.getId(),customer2);
        customerMap.put(customer3.getId(),customer3);
    }
    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCostumerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {

        Customer savedNewCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .build();

        customerMap.put(savedNewCustomer.getId(),savedNewCustomer);

        return savedNewCustomer;
    }

    @Override
    public void updatedCustomerId(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());
        customerMap.put(existing.getId(), existing);
    }

    @Override
    public void deleteById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerId(UUID customerId, Customer customer) {

        Customer existing = customerMap.get(customerId);

        if(customer.getCustomerName() !=null){
            existing.setCustomerName(customer.getCustomerName());
        }
    }
}
