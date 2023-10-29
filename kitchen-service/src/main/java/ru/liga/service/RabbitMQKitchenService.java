package ru.liga.service;

import ru.liga.entity.Courier;

import java.util.List;

public interface RabbitMQKitchenService {
    void sendMessage(String message, String routingKey);
    void sendMessageForDelivery(String message, List<Courier> couriers, Long order_id);
}
