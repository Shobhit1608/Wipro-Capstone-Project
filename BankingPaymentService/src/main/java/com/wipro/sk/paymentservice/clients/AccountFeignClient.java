package com.wipro.sk.paymentservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.sk.paymentservice.dtos.AccountDto;

@FeignClient(name = "BankingAccountService")
public interface AccountFeignClient {

	@GetMapping("/api/accounts/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable Long id);
	
	@PostMapping("/api/accounts/updatebalance/{id}")
	public ResponseEntity<Void> updateBalance(@PathVariable Long id, @RequestParam double newBalance) ;
}
