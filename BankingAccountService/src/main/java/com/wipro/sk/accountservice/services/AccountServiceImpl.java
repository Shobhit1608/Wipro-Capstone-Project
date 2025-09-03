package com.wipro.sk.accountservice.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.sk.accountservice.clients.CustomerFeignClient;
import com.wipro.sk.accountservice.dtos.CreateAccountRequest;
import com.wipro.sk.accountservice.dtos.CustomerDto;
import com.wipro.sk.accountservice.entities.Account;
import com.wipro.sk.accountservice.exceptions.AccountNotFoundException;
import com.wipro.sk.accountservice.repositories.AccountRepository;
import com.wipro.sk.customerservice.exceptions.CustomerNotFoundException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
    private final CustomerFeignClient customerFeignClient;
    ResponseEntity<CustomerDto> customerResponse;
    
	@Override
	public Account createAccount(CreateAccountRequest request, Long customerId) {
		 CustomerDto customer;
	        try {
	            ResponseEntity<CustomerDto> customerResponse = customerFeignClient.getCustomerById(customerId);

	            // Check for a null response or a non-200 status code
	            if (customerResponse == null || !customerResponse.getStatusCode().is2xxSuccessful() || customerResponse.getBody() == null) {
	                throw new CustomerNotFoundException("Failed to retrieve valid customer details for ID: " + customerId);
	            }
	            customer = customerResponse.getBody();

	            if (customer.getEmail() == null || customer.getEmail().isBlank()) {
	                throw new CustomerNotFoundException("Customer with ID " + customerId + " is missing an email address.");
	            }

	        } catch (FeignException.NotFound e) {
	            // This handles the case where the CustomerService returns a 404
	            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
	        } catch (Exception e) {
	            // This handles other communication errors (e.g., service is down)
	            throw new IllegalStateException("Error communicating with Customer Service: " + e.getMessage(), e);
	        }
		

		Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword()); 
        account.setEmail(customer.getEmail());
        account.setAccountType(request.getAccountType());
        account.setBalance(request.getBalance());
        account.setPanCardNumber(request.getPanCardNumber());
        account.setAadharCardNumber(request.getAadharCardNumber());
        account.setHasLoan(request.isHasLoan());

        account.setCustomerId(customerId);
        account.setAccountNumber(UUID.randomUUID().toString());

        return accountRepository.save(account);
	}

	 @Override
	    public Account getAccountById(Long id) {
	        return accountRepository.findById(id)
	                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
	    }

	 @Override
	 @Transactional
	 public void updateBalance(Long id, double newBalance) {
		 Account account = getAccountById(id);
		 account.setBalance(newBalance);
	        accountRepository.save(account);
		
	 }

	 	

}
