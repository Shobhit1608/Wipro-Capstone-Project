package com.wipro.sk.paymentservice.dtos;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NotificationRequestDto {
    private Long paymentId;
    private Long senderAccountId;
    private String senderAccountNumber;
    private double amount;
    private LocalDateTime transactionTime;
}
