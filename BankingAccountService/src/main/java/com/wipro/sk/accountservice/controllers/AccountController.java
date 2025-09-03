package com.wipro.sk.accountservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sk.accountservice.dtos.CreateAccountRequest;
import com.wipro.sk.accountservice.entities.Account;
import com.wipro.sk.accountservice.services.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

	private final AccountService accountService;
	
	@PostMapping("/create/{customerId}")
    public ResponseEntity<Account> createAccount(@PathVariable Long customerId, @Valid @RequestBody CreateAccountRequest request) {
        Account createdAccount = accountService.createAccount(request, customerId);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }
    
    @PostMapping("/updatebalance/{id}")
    public ResponseEntity<Void> updateBalance(@PathVariable Long id, @RequestParam double newBalance) {
        accountService.updateBalance(id, newBalance);
        return ResponseEntity.ok().build();
    }
}