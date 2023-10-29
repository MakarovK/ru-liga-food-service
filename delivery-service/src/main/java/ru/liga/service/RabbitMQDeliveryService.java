package ru.liga.service;

public interface RabbitMQDeliveryService {
    void sendMessage(String message);
}
