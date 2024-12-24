package org.example.notificationsservice.service;

@Service
public class NotificationService {
    public void sendNotification(String message) {
        System.out.println("Sending notification to user: " + message);
        //TODO: Add logic to send notification (e.g., via email, SMS, or push notification)
    }
}

