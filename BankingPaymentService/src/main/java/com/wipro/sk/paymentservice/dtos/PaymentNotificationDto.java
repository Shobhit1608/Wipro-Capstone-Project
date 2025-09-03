package com.wipro.sk.paymentservice.dtos;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentNotificationDto {
    private Long paymentId;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    private LocalDateTime transactionTime;
    private boolean successful;
    private String statusMessage;
}
