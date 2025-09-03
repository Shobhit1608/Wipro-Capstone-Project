package com.wipro.sk.paymentservice.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditDto {
    private Long paymentId;
    private Long senderCustomerId;
    private Long receiverCustomerId;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    private LocalDateTime transactionTime;
    private boolean successful;
    private String statusMessage;
}