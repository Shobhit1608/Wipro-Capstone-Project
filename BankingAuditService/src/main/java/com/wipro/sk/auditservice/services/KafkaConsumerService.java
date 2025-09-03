//package com.wipro.sk.auditservice.services;
//
//import com.wipro.sk.auditservice.client.AccountFeignClient;
//import com.wipro.sk.auditservice.dtos.AccountDto;
//import com.wipro.sk.auditservice.dtos.PaymentNotificationDto;
//import com.wipro.sk.auditservice.entities.Audit;
//import com.wipro.sk.auditservice.repositories.AuditRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class KafkaConsumerService {
//
//    private final AuditRepository auditRepository;
//    private final AccountFeignClient accountFeignClient;
//
//    @KafkaListener(topics = "payment-notifications", groupId = "audit-group")
//    public void consumePaymentEvent(PaymentNotificationDto event) {
//        log.info("📩 Received payment event for auditing: {}", event);
//        try {
//        	AccountDto sender = accountFeignClient
//                    .getAccountById(event.getSenderAccountId())
//                    .getBody();
//
//            AccountDto receiver = accountFeignClient
//                    .getAccountById(event.getReceiverAccountId())
//                    .getBody();
//
//            Audit audit = new Audit();
//            audit.setPaymentId(event.getPaymentId());
//            audit.setSenderCustomerId(sender != null ? sender.getCustomerId() : null);
//            audit.setReceiverCustomerId(receiver != null ? receiver.getCustomerId() : null);
//            audit.setSenderAccountNumber(event.getSenderAccountNumber());
//            audit.setReceiverAccountNumber(event.getReceiverAccountNumber());
//            audit.setAmount(event.getAmount());
//            audit.setTransactionTime(event.getTransactionTime());
//            audit.setSuccessful(event.isSuccessful());
//            audit.setStatusMessage(event.getStatusMessage());
//            audit.setAuditedAt(LocalDateTime.now());
//
//            auditRepository.save(audit);
//            log.info("✅ Successfully audited payment event for payment ID: {}", event.getPaymentId());
//        } catch (Exception e) {
//            log.error("❌ Error while auditing payment event for payment ID {}: {}", 
//                      event.getPaymentId(), e.getMessage());
//        }
//    }
//}
