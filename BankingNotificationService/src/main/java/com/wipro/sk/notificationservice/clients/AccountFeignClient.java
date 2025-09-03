package com.wipro.sk.notificationservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sk.notificationservice.dtos.AccountDto;

@FeignClient(name = "BankingAccountService")
public interface AccountFeignClient {
    @GetMapping("/api/accounts/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable Long id);
}