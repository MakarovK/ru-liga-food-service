package ru.liga.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDeliveryServiceImpl implements RabbitMQDeliveryService{
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQDeliveryServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("Delivery-queue-response", message);
    }
}
