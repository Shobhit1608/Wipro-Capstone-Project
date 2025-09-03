package com.wipro.sk.notificationservice.dtos;

import lombok.Data;

@Data
public class AccountDto {
 private Long id;
 private Long customerId;
 private String email; 
 private double balance;
}
