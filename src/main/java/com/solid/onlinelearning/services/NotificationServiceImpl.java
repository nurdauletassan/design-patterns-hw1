package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.NotificationService;


public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(String to, String message) {
        System.out.println("[EMAIL] Sending to " + to + ": " + message);
    }
}
