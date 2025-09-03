package com.wipro.sk.accountservice.entities;

import com.wipro.sk.accountservice.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "accounts")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Customer ID cannot be null")
    @Column(name = "customer_id")
    private Long customerId;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Account number cannot be blank")
    @Column(unique = true, nullable = false)
    private String accountNumber;

    @NotNull(message = "Account type cannot be null")
    @Enumerated(EnumType.STRING) 
    private AccountType accountType;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private double balance;

    @NotBlank(message = "PAN card number cannot be blank")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN card number format")
    private String panCardNumber;

    @NotBlank(message = "Aadhar card number cannot be blank")
    @Pattern(regexp = "^\\d{12}$", message = "Aadhar number must be 12 digits")
    private String aadharCardNumber;
    
    @NotBlank(message = "Email cannot be blank")
    private String email;

    private boolean hasLoan = false;
}
