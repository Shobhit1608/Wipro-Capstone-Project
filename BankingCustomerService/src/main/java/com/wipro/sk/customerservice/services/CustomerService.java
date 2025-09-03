package com.wipro.sk.customerservice.services;

import java.util.Optional;

import com.wipro.sk.customerservice.entities.Customer;

import jakarta.validation.Valid;

public interface CustomerService {

	Customer saveCustomer(@Valid Customer customer);

	Optional<Customer> getCustomerById(Long id);

	Optional<Customer> updateCustomer(Long id, Customer customerDetails);
    

}
