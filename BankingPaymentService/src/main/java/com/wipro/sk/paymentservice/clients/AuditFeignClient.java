package com.wipro.sk.paymentservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.wipro.sk.paymentservice.dtos.AuditDto;

@FeignClient(name = "BankingAuditService")
public interface AuditFeignClient {

	@PostMapping("/api/audits/create")
    ResponseEntity<AuditDto> createAudit(AuditDto auditDto);
}
