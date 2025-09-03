package com.wipro.sk.notificationservice.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.sk.notificationservice.clients.AccountFeignClient;
import com.wipro.sk.notificationservice.dtos.AccountDto;
import com.wipro.sk.notificationservice.dtos.NotificationRequestDto;
import com.wipro.sk.notificationservice.entities.Notification;
import com.wipro.sk.notificationservice.enums.NotificationStatus;
import com.wipro.sk.notificationservice.repositories.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{

	 private final NotificationRepository notificationRepository;
	    private final AccountFeignClient accountFeignClient;
	    private final EmailService emailService;
	    
	    
		@Override
		public List<Notification> getAllNotifications() {
			return notificationRepository.findAll();
		}
		 @Override
		    public void sendPaymentNotification(NotificationRequestDto requestDto) {
		        log.info("Processing notification for payment ID: {}", requestDto.getPaymentId());
		        Notification notification = new Notification();
		        notification.setPaymentId(requestDto.getPaymentId());
		        notification.setSentAt(LocalDateTime.now());

		        try {
		            AccountDto senderAccount = accountFeignClient.getAccountById(requestDto.getSenderAccountId()).getBody();
		            if (senderAccount == null || senderAccount.getEmail() == null || senderAccount.getEmail().isBlank()) {
		                throw new IllegalStateException("Sender account details or email could not be fetched.");
		            }

		            notification.setCustomerId(senderAccount.getCustomerId());
		            notification.setRecipientEmail(senderAccount.getEmail());

		            String subject = "Transaction Alert: Debit from your Account";
		            String body = String.format(
		                "Dear Customer,\n\nAn amount of INR %.2f has been debited from your account ending in ...%s on %s." +
		                "\n\nYour available balance is now INR %.2f.\n\nThank you for banking with us.",
		                requestDto.getAmount(),
		                requestDto.getSenderAccountNumber().substring(requestDto.getSenderAccountNumber().length() - 4),
		                requestDto.getTransactionTime().toLocalDate(),
		                senderAccount.getBalance()
		            );

		            emailService.sendEmail(senderAccount.getEmail(), subject, body);

		            notification.setMessage(body);
		            notification.setStatus(NotificationStatus.SENT);
		            log.info("Successfully processed and sent notification for payment ID {}", requestDto.getPaymentId());

		        } catch (Exception e) {
		            log.error("Failed to process notification for payment ID {}: {}", requestDto.getPaymentId(), e.getMessage());
		            notification.setStatus(NotificationStatus.FAILED);
		            notification.setMessage(e.getMessage());
		            if (notification.getCustomerId() == null) {
		                notification.setCustomerId(-1L);
		            }
		        } finally {
		            notificationRepository.save(notification);
		        }
		    }
	    
	    
}
