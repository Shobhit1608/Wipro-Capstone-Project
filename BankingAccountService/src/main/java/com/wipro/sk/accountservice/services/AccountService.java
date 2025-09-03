	package com.wipro.sk.accountservice.services;
	
	import com.wipro.sk.accountservice.dtos.CreateAccountRequest;
	import com.wipro.sk.accountservice.entities.Account;
	
	
	public interface AccountService {
	
		 Account createAccount(CreateAccountRequest request, Long customerId);
		Account getAccountById(Long id);
		void updateBalance(Long id, double newBalance);
	
	}
