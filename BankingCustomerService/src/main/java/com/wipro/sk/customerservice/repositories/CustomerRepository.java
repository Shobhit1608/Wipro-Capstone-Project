package com.wipro.sk.customerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sk.customerservice.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
