package com.wipro.sk.auditservice.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentNotificationDto {
	private Long paymentId;
    private Long senderAccountId;      
    private Long receiverAccountId;    
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    private LocalDateTime transactionTime;
    private boolean successful;
    private String statusMessage;
}
