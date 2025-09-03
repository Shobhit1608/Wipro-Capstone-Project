package com.wipro.sk.paymentservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.sk.paymentservice.dtos.NotificationRequestDto;

@FeignClient(name = "BankingNotificationService")
public interface NotificationFeignClient {
    @PostMapping("/api/notifications/send")
    void sendNotification(@RequestBody NotificationRequestDto requestDto);
}

