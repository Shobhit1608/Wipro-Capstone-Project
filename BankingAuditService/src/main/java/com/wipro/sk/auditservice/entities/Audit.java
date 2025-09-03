package com.wipro.sk.auditservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment_audits")
public class Audit {
    @Id
    private Long paymentId;
    
    private Long senderCustomerId;
    private Long receiverCustomerId;

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    private LocalDateTime transactionTime;
    private boolean successful;
    private String statusMessage;
    private LocalDateTime auditedAt;
}

