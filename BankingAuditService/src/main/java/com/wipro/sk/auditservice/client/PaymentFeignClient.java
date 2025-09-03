package com.wipro.sk.auditservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sk.auditservice.dtos.PaymentNotificationDto;

@FeignClient(name = "BankingPaymentService")
public interface PaymentFeignClient {

	@GetMapping("/api/payments/{id}")
    ResponseEntity<PaymentNotificationDto> getPaymentById(@PathVariable Long id);
}
