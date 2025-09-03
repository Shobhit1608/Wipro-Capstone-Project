package com.wipro.sk.notificationservice.services;

import java.util.List;

import com.wipro.sk.notificationservice.dtos.NotificationRequestDto;
import com.wipro.sk.notificationservice.entities.Notification;

public interface NotificationService {

	List<Notification> getAllNotifications();

	void sendPaymentNotification(NotificationRequestDto requestDto);

	
}
