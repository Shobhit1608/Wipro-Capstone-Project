//package com.wipro.sk.paymentservice.services;
//
//import com.wipro.sk.paymentservice.config.KafkaTopicConfig;
//import com.wipro.sk.paymentservice.dtos.PaymentNotificationDto;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class KafkaProducerService {
//
//    private final KafkaTemplate<String, PaymentNotificationDto> kafkaTemplate;
//
//    public void sendPaymentNotification(PaymentNotificationDto notification) {
//        log.info("Sending payment notification to Kafka: {}", notification);
//        kafkaTemplate.send(KafkaTopicConfig.PAYMENT_TOPIC, notification.getPaymentId().toString(), notification);
//        log.info("Notification sent successfully.");
//    }
//}