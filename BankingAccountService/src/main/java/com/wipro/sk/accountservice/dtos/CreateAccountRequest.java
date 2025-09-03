package com.wipro.sk.accountservice.dtos;

import lombok.Data;

import com.wipro.sk.accountservice.enums.AccountType;

import jakarta.validation.constraints.*;

@Data
public class CreateAccountRequest {

	@NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Account type cannot be null")
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

    private boolean hasLoan = false;
}
