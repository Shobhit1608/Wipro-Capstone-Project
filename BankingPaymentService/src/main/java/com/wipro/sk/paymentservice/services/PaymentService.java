package com.wipro.sk.paymentservice.services;

import java.util.Optional;

import com.wipro.sk.paymentservice.dtos.PaymentRequestDto;
import com.wipro.sk.paymentservice.entities.Payment;

public interface PaymentService {

    Payment processPayment(PaymentRequestDto paymentRequest);

	Optional<Payment> getPaymentById(Long id);

}
