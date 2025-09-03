//package com.wipro.sk.paymentservice.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    public static final String PAYMENT_TOPIC = "payment-notifications";
//
//    @Bean
//    public NewTopic paymentTopic() {
//        return TopicBuilder.name(PAYMENT_TOPIC)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//}