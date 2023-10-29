package ru.liga.service;

public interface RabbitMQKitchenService {
    void sendMessage(String message, String routingKey);
}
