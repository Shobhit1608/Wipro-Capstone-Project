package com.wipro.sk.paymentservice.services;

import com.wipro.sk.paymentservice.clients.AccountFeignClient;
import com.wipro.sk.paymentservice.clients.AuditFeignClient;
import com.wipro.sk.paymentservice.dtos.AccountDto;
import com.wipro.sk.paymentservice.dtos.AuditDto;
import com.wipro.sk.paymentservice.dtos.PaymentRequestDto;
import com.wipro.sk.paymentservice.entities.Payment;
import com.wipro.sk.paymentservice.exceptions.InsufficientBalanceException;
import com.wipro.sk.paymentservice.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountFeignClient accountFeignClient;
    private final AuditFeignClient auditFeignClient;

//    private final KafkaProducerService kafkaProducerService;
    
    @Override
    @Transactional
    public Payment processPayment(PaymentRequestDto paymentRequest) {
        log.info("Processing payment from account ID {} to account ID {}",
                paymentRequest.getSenderAccountId(), paymentRequest.getReceiverAccountId());

        AccountDto senderAccount = accountFeignClient.getAccountById(paymentRequest.getSenderAccountId()).getBody();
        AccountDto receiverAccount = accountFeignClient.getAccountById(paymentRequest.getReceiverAccountId()).getBody();

        if (senderAccount.getBalance() < paymentRequest.getAmount()) {
            log.warn("Insufficient balance for sender account ID: {}", senderAccount.getId());
            throw new InsufficientBalanceException("Sender does not have enough balance for this transaction.");
        }

        log.info("Updating balances for sender ID {} and receiver ID {}", senderAccount.getId(), receiverAccount.getId());
        
        double newSenderBalance = senderAccount.getBalance() - paymentRequest.getAmount();
        accountFeignClient.updateBalance(senderAccount.getId(), newSenderBalance);

        double newReceiverBalance = receiverAccount.getBalance() + paymentRequest.getAmount();
        accountFeignClient.updateBalance(receiverAccount.getId(), newReceiverBalance);
        
        log.info("Balances updated successfully.");

        Payment payment = new Payment();
        payment.setSenderAccountNumber(senderAccount.getAccountNumber());
        payment.setReceiverAccountNumber(receiverAccount.getAccountNumber());
        payment.setAmount(paymentRequest.getAmount());
        payment.setTransactionTime(LocalDateTime.now());
        payment.setPaymentDone(true);
        Payment savedPayment = paymentRepository.save(payment);

        log.info("Payment transaction saved successfully with ID: {}", savedPayment.getId());
        
        try {
            AuditDto auditDto = new AuditDto();
            auditDto.setPaymentId(savedPayment.getId());
            auditDto.setSenderCustomerId(senderAccount.getId());
            auditDto.setReceiverCustomerId(receiverAccount.getId());
            auditDto.setSenderAccountNumber(savedPayment.getSenderAccountNumber());
            auditDto.setReceiverAccountNumber(savedPayment.getReceiverAccountNumber());
            auditDto.setAmount(savedPayment.getAmount());
            auditDto.setTransactionTime(savedPayment.getTransactionTime());
            auditDto.setSuccessful(true);
            auditDto.setStatusMessage("Payment completed successfully.");

            auditFeignClient.createAudit(auditDto);
            log.info("✅ Audit log created for paymentId={}", savedPayment.getId());
        } catch (Exception e) {
            log.error("❌ Failed to create audit log for paymentId={}", savedPayment.getId(), e);
        }
        
//        PaymentNotificationDto notification = PaymentNotificationDto.builder()
//                .paymentId(savedPayment.getId())
//                .senderAccountNumber(savedPayment.getSenderAccountNumber())
//                .receiverAccountNumber(savedPayment.getReceiverAccountNumber())
//                .amount(savedPayment.getAmount())
//                .transactionTime(savedPayment.getTransactionTime())
//                .successful(true)
//                .statusMessage("Payment completed successfully.")
//                .build();
//       kafkaProducerService.sendPaymentNotification(notification);
        
        return savedPayment;
    }

	@Override
	public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);

	}
}

