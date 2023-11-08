package ru.liga.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;

@Component
@Slf4j
public class DeliveryListener {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Delivery-queue-courier1")
    public void receiveMessageForCourier1(String message) {
        OrderDTO orderDTO;
        try {
            orderDTO = objectMapper.readValue(message, OrderDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пришёл заказ № " + orderDTO.getId() + " для курьера 1. Принять или отклонить заказ?");

    }

    @RabbitListener(queues = "Delivery-queue-courier2")
    public void receiveMessageForCourier2(String message) {
        OrderDTO orderDTO;
        try {
            orderDTO = objectMapper.readValue(message, OrderDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пришёл заказ № " + orderDTO.getId() + " для курьера 2. Принять или отклонить заказ?");
    }
}

