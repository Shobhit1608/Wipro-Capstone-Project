package com.wipro.sk.accountservice.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.sk.accountservice.dtos.CustomerDto;

@FeignClient(name = "BankingCustomerService") 
public interface CustomerFeignClient {

    @GetMapping("/api/customers/{id}") 
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id);

}
