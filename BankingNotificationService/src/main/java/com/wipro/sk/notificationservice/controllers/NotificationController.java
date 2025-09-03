package com.wipro.sk.notificationservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sk.notificationservice.dtos.NotificationRequestDto;
import com.wipro.sk.notificationservice.entities.Notification;
import com.wipro.sk.notificationservice.services.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

	 @GetMapping
	    public ResponseEntity<List<Notification>> getAllNotifications() {
	        return ResponseEntity.ok(notificationService.getAllNotifications());
	    }

	    @PostMapping("/send")
	    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequestDto requestDto) {
	        notificationService.sendPaymentNotification(requestDto);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    }
	
}
