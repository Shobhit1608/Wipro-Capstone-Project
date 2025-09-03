package com.wipro.sk.paymentservice.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestDto {

    @NotNull(message = "Sender account ID cannot be null")
    private Long senderAccountId;

    @NotNull(message = "Receiver account ID cannot be null")
    private Long receiverAccountId;

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private double amount;
}
