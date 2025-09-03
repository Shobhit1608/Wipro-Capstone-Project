package com.wipro.sk.notificationservice.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationRequestDto {
    private Long paymentId;
    private Long senderAccountId;
    private String senderAccountNumber;
    private double amount;
    private LocalDateTime transactionTime;
}