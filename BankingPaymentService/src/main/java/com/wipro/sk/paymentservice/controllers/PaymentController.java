package com.wipro.sk.paymentservice.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sk.paymentservice.dtos.PaymentRequestDto;
import com.wipro.sk.paymentservice.entities.Payment;
import com.wipro.sk.paymentservice.services.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

	 private final PaymentService paymentService;
	 
	  @PostMapping("/doPayment")
	    public ResponseEntity<Payment> doPayment(@Valid @RequestBody PaymentRequestDto requestDto) {
	        log.info("Initiating payment: {}", requestDto);
	        Payment payment = paymentService.processPayment(requestDto);
	        return new ResponseEntity<>(payment, HttpStatus.CREATED);
	    }
	  @GetMapping("/{id}")
	  public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		  log.info("Fetching payment details for ID: {}", id);
		  Optional<Payment> payment = paymentService.getPaymentById(id);
		  return payment.map(ResponseEntity::ok)
				  .orElse(ResponseEntity.notFound().build());
	  }
	  
}
