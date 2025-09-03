package com.wipro.sk.customerservice.services;

import com.wipro.sk.customerservice.entities.Customer;
import com.wipro.sk.customerservice.exceptions.CustomerNotFoundException;
import com.wipro.sk.customerservice.repositories.CustomerRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(@Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return Optional.of(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id)));
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

        // only set non-null fields
        Optional.ofNullable(customerDetails.getName()).ifPresent(existing::setName);
        Optional.ofNullable(customerDetails.getEmail()).ifPresent(existing::setEmail);
        Optional.ofNullable(customerDetails.getMobileNumber()).ifPresent(existing::setMobileNumber);
        Optional.ofNullable(customerDetails.getAddress()).ifPresent(existing::setAddress);
        Optional.ofNullable(customerDetails.getAge()).ifPresent(existing::setAge);
        Optional.ofNullable(customerDetails.getGender()).ifPresent(existing::setGender);

        return Optional.of(customerRepository.save(existing));
    }
}
