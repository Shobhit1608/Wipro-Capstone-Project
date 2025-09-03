package com.wipro.sk.notificationservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

	 private final JavaMailSender mailSender;

	    @Value("${spring.mail.username}")
	    private String fromEmail;

	    public void sendEmail(String to, String subject, String body) {
	        try {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setFrom(fromEmail);
	            message.setTo(to);
	            message.setSubject(subject);
	            message.setText(body);
	            mailSender.send(message);
	            log.info("Successfully sent email to {}", to);
	        } catch (Exception e) {
	            log.error("Failed to send email to {}: {}", to, e.getMessage());
	            throw new RuntimeException("Error while sending email", e);
	        }
	    }
}
