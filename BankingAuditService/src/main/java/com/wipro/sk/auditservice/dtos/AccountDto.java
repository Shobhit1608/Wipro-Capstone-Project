package com.wipro.sk.auditservice.dtos;

import lombok.Data;

@Data
public class AccountDto {
 private Long id;
 private String accountNumber;
 private double balance;
 private Long customerId;
}
