package org.example.notificationsservice.kafka;

import org.example.notificationsservice.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "commande-events", groupId = "notifications-group")
    public void consumeCommandeEvent(String message) {
        System.out.println("Received Kafka message: " + message);
        notificationService.sendNotification(message);
    }
}
