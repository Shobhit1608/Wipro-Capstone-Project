package com.wipro.sk.paymentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sk.paymentservice.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
